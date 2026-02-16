# Metric Decomposition

```python
import pandas as pd
import sympy as sp
import numpy as np

class ImpactExplainer:
    def __init__(self, expression_str, variables):
        self.var_names = list(variables)
        self.symbols = sp.symbols(self.var_names)
        self.expr = sp.expand(expression_str)
        
        # Split into individual terms (e.g., x1, x1*x2, etc.)
        self.terms = sp.Add.make_args(self.expr)
        
        # Pre-compile the formula for each term and its partial derivatives
        self.term_data = []
        for term in self.terms:
            self.term_data.append({
                'term': term,
                'func': sp.lambdify(self.symbols, term, "numpy"),
                # Derivatives of this specific term w.r.t each variable
                'derivs': [sp.lambdify(self.symbols, sp.diff(term, v), "numpy") for v in self.symbols]
            })

    def fit(self, X, y=None):
        base_vals = [X[v].to_numpy() for v in self.var_names]
        deltas = [X[f"{v}_delta"].to_numpy() for v in self.var_names]
        new_vals = [b + d for b, d in zip(base_vals, deltas)]
        
        # We will store impacts for singular variables and specific mixed terms
        impact_dict = {f"y_delta_{v}": np.zeros(len(X)) for v in self.var_names}
        
        for item in self.term_data:
            term = item['term']
            
            # 1. Calculate Total Delta for this specific term
            t_base = item['func'](*base_vals)
            t_new = item['func'](*new_vals)
            total_term_delta = t_new - t_base
            
            # 2. Extract Singular Contributions from this term
            term_singular_sum = np.zeros(len(X))
            for i, v in enumerate(self.var_names):
                d_val = item['derivs'][i](*base_vals)
                # Handle potential scalar returns from lambdify
                if not isinstance(d_val, np.ndarray): d_val = np.full(len(X), d_val)
                
                contribution = d_val * deltas[i]
                impact_dict[f"y_delta_{v}"] += contribution
                term_singular_sum += contribution
            
            # 3. If it's a mixed term, the "Leftover" is the specific mixed effect
            if len(term.free_symbols) > 1:
                mixed_val = total_term_delta - term_singular_sum
                impact_dict[f"y_delta_mixed_{term}"] = mixed_val
            
        self.impact_df = pd.DataFrame(impact_dict, index=X.index)
        # Reorder: Singular variables first, then mixed terms
        cols = [c for c in self.impact_df.columns if "mixed" not in c] + \
               [c for c in self.impact_df.columns if "mixed" in c]
        self.impact_df = self.impact_df[cols]
        
        return self

    def transform(self, X, y=None):
        return pd.concat([X, self.impact_df], axis=1)

    def fit_transform(self, X, y=None):
        return self.fit(X).transform(X)
        
    def to_sql(self, table_name="input_table"):
        """Generates a SQL query to perform the same attribution logic."""
        
        def sql_format(expr):
            return str(expr).replace('**', '^')

        # 1. Prepare Base CTE: Define the 'New' values
        new_val_cols = [f"({v} + {v}_delta) AS {v}_new" for v in self.var_names]
        
        # 2. Singular Impacts: Sum of partial derivatives across all terms
        singular_sqls = []
        for v in self.symbols:
            deriv = sp.diff(self.expr, v)
            singular_sqls.append(f"({sql_format(deriv)}) * {v}_delta AS y_delta_{v}")

        # 3. Mixed Impacts: Term-specific residue
        mixed_sqls = []
        for term in self.terms:
            if len(term.free_symbols) > 1:
                # Replace original vars with 'new' vars for the final state of the term
                term_new = term
                singular_contribution = 0
                for v in term.free_symbols:
                    term_new = term_new.subs(v, sp.Symbol(f"{v}_new"))
                    singular_contribution += sp.diff(term, v) * sp.Symbol(f"{v}_delta")
                
                term_mixed_expr = (term_new - term) - singular_contribution
                mixed_sqls.append(f"{sql_format(term_mixed_expr)} AS y_delta_mixed_{sql_format(term)}")

        # Assemble the SQL string
        sql = f"WITH base AS (\n  SELECT *,\n    {',\n    '.join(new_val_cols)}\n  FROM {table_name}\n)\n"
        sql += "SELECT\n  *,\n  "
        sql += ",\n  ".join(singular_sqls + mixed_sqls)
        sql += "\nFROM base;"
        return sql
```



```python
explainer = ImpactExplainer("x1*x2 + x3/x1", ["x1", "x2", "x3"])

df_input = pd.DataFrame({
    'x1': [2], 'x2': [2], 'x3': [2],
    'x1_delta': [2], 'x2_delta': [1], 'x3_delta': [0]
})

analysis_df = explainer.fit_transform(df_input)
analysis_df

print(explainer.to_sql())
```

Since SQL doesn't have a symbolic engine like SymPy, we need to "hardcode" the logic for each term and its derivatives.