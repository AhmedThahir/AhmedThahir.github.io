# Custom 

## Regression with Custom Loss Function

```python
import utils

from sklearn.base import BaseEstimator
#from sklearn.utils.estimator_checks import check_estimator
from sklearn.utils.validation import check_X_y, check_array, check_is_fitted
from sklearn.metrics import (
    mean_absolute_percentage_error as mape,
    # mean_squared_error as mse,
    root_mean_squared_error as rmse,
    mean_absolute_error as mae,
    r2_score as r2
)

import numpy as np
import pandas as pd
from scipy import optimize as o, stats

from inspect import getfullargspec, getsource
# import copy
```

```python
class CustomRegression(BaseEstimator):
    """
    All variables inside the Class should end with underscore
    """
    def __init__(self, model, method="Nelder-Mead", cost = None, alpha=0, l1_ratio=0.5, maxiter = 500, maxfev = 1_000):
        self.model = model
        self.method = method
        self.cost = cost if cost is not None else self.mse
        self.alpha = alpha
        self.l1_ratio = l1_ratio
        self.maxiter = maxiter
        self.maxfev = maxfev

    def __str__(self):
        return str(self.model_)
    def __repr__(self):
        return str(self)
    
    def mse(self, pred, true, sample_weight):
        error = pred - true
        
        cost = error**2

        cost = (
            np.mean( # median is robust to outliers than mean
                sample_weight * cost
            )
        )

        return cost
   
    def l1(self, params):
        return np.sum(np.abs(params-self.model_.param_initial_guess))
    def l2(self, params):
        return np.sum((params-self.model_.param_initial_guess) ** 2)
    def l3(self, params, l1_ratio=0.5):
        return (
            l1_ratio * self.l1(params) +
            (1 - l1_ratio) * self.l2(params)
        )

    def regularization_cost(self, params, alpha, penalty_type="l3"):
        """
        Regularization requires standardised parameters
        """
        penalty = get_attr(self, penalty_type)(params, self.l1_ratio)

        return (alpha * penalty)/self.sample_size_

    def cost_total(self, params, X, y):
        pred = self.model_.equation(X, *params)
        cost = self.cost(y, pred, self.sample_weight)

        if self.alpha == 0:
            pass
        else:
            cost += self.regularization_cost(params, self.alpha)
        return cost

    def check_enough_samples(self):
        return self.enough_samples_

    def fit(self, X, y, sample_weight=None):
        check_X_y(X, y) # Using self.X,self.y = check_X_y(self.X,self.y) removes column names

        self.X, self.y = X, y

        self.n_features_in_ = self.X.shape[1]

        self.sample_size_ = self.X.shape[0]
        
        self.sample_weight = (
            sample_weight
            if sample_weight is not None
            else np.full(self.sample_size_, 1) # set Sample_Weight as 1 by default
        )

        self.sample_size_ = self.sample_weight[self.sample_weight > 0].shape[0]

        self.model_ = copy.deepcopy(self.model)

        self.dof_ = self.sample_size_ - self.model_.k # - 1 # n-k-1
        if self.dof_ <= 0:
            self.success_ = False
            self.enough_samples_ = False
            # raise Exception("Not enough samples")
            return self
        else:
            self.enough_samples_ = True

        params_all = getfullargspec(self.model_.equation).args        
        params = [param for param in params_all if param not in ['self', "x"]]

        self.optimization_ = o.minimize(
            self.cost_total,
            x0 = self.model_.param_initial_guess,
            args = (self.X, self.y),
            method = self.method, # "L-BFGS-B", "Nelder-Mead", "SLSQP",
            constraints = self.model_.constraints,
            bounds = self.model_.param_bounds,
            # [
            #     (-1, None) for param in params # variables must be positive
            # ]
            options = dict(
                maxiter = self.maxiter,
                maxfev = self.maxfev,
                # xatol=1e-4,
                # fatol=1e-4,
                # adaptive=False,
            )
        )
        self.success_ = self.optimization_.success

        self.fitted_coeff_ = (
            self.optimization_.x
        )

        self.fitted_coeff_formatted_ = [
            f"""{{ {utils.round_f(popt, 4)} }}""" for popt in self.fitted_coeff_
        ]
            
        self.model_.set_fitted_coeff(*self.fitted_coeff_)
        self.model_.set_fitted_coeff_formatted(*self.fitted_coeff_formatted_)

        self.rmse_response = root_mean_squared_error(
            y,
            self.output(X),
        )
        self.rmse_link = root_mean_squared_error(
            y,
            self.output(X),
        )
        
        return self
    
    def output(self, X):
        return np.array(
            self.model_
            .equation(X, *self.fitted_coeff_)
        )
    
    def get_pred_se(self, X):
        return self.rmse_response

    def predict(self, X):
        check_is_fitted(self, "fitted_coeff_") # Check to verify if .fit() has been called
        check_array(X) #X = check_array(X) # removes column names

        pred = (
            self.output(X)
            .astype(np.float32)
        )

        return pred

    def predict_quantile(self, X, q):
        return self.model_.quantile(X, self.X, self.y, link_distribution_dof=self.dof_, link_distribution_q=q)


class CustomRegressionGrouped(CustomRegression):
    def __str__(self):
        x = ""
        for key, value in self.get_params().items():
            x += f"{str(key)}: {str([utils.round_f(v, 4) for v in list(value)])} \n\n"
        
        return str(x)
    def __init__(self, model, cost, method="Nelder-Mead", group=None):
        super().__init__(model=model, cost=cost, method=method)
        self.group = group
        self.model = model
        self.method = method
        self.cost = cost

        self.optimization_ = dict()
        self.model_ = dict()
        self.enough_samples_ = dict()
        
        self.dof_ = 0
        self.sample_size_ = 0
    
    def get_params(self, how="dict"):
        params = dict()
        for key, value in self.model_.items():
            popt = "fitted_coeff_"
            if popt in dir(value):
                params[key] = getattr(value, popt)
            else:
                params[key] = None
            
        if how == "df":
            params = pd.DataFrame(params).T
        return params
    
    def model_group(self, X, y, model, method, error, sample_weight):
        return (
            CustomRegression(
                model = self.model,
                cost = self.cost,
                method = self.method,
            )
            .fit(
                X,
                y,
                sample_weight
            )
        )
        
    def check_enough_samples(self, how="all"):
        if how == "all":
            enough_samples = True
            for e in self.enough_samples_.values():
                if not e:
                    enough_samples = False
        elif how == "any":
            enough_samples = self.enough_samples_
        else:
            pass
                
        return enough_samples

    def apply_model_multiple_group(self, X, y, group, model, method, cost, sample_weight):
        for g in X[self.group].unique():
            mask = X.eval(f"{self.group} == {g}")
                
            m = self.model_group(
                X[mask],
                y[mask],
                model,
                method,
                cost,
                sample_weight[mask] if sample_weight is not None else sample_weight
            )

            if m.success_:
                self.model_[g] = m
                self.enough_samples_[g] = m.enough_samples_
                self.optimization_[g] = m.optimization_
                self.sample_size_ += m.sample_size_
        
        success = True
        for o in self.optimization_.values():
            if not o.success:
                success = False
                
        self.success_ = success
            
    def fit(self, X, y, sample_weight=None):
        self.model_ = dict()

        self.apply_model_multiple_group(X, y, self.group, self.model, self.method, self.cost, sample_weight)
    def predict(self, X):
        Xs = []
        preds = pd.DataFrame()
        
        for g in X[self.group].unique():
            if g not in self.model_.keys():
                return
            else:
                Xg = X.query(f"{self.group} == {g}")
                index = Xg.index

                pred = self.model_[g].predict(
                    X = Xg.copy().reset_index(drop=True),
                )

                preds = pd.concat([
                    preds, pd.Series(pred, index=index)
                ])
        return preds.sort_index()
    
    def predict_quantile(self, X, q):
        Xs = []
        preds = pd.DataFrame()
        
        for g in X[self.group].unique():
            if g not in self.model_.keys():
                return Exception(f"Model not trained for {g}")
            else:
                Xg = X.query(f"{self.group} == {g}")
                index = Xg.index

                pred = self.model_[g].predict_quantile(
                    X = Xg.copy().reset_index(drop=True),
                    q = q
                )

                preds = pd.concat([
                    preds, pd.Series(pred, index=index)
                ])
        
        return preds.sort_index()
```

```python
curve_fit = CustomRegression(
	model = model_selected,
	cost = regression.LogCosh().cost,
	method = solver
)
print(curve_fit) ## prints latex

curve_fit.fit(
	X_train,
	y_train,
	sample_weight=df_train["Sample_Weight"],
)

print(curve_fit) ## prints latex with coefficent values

pred = curve_fit.predict(X_test)
ll = curve_fit.predict_quantile(X_test, q=0.025)
ul = curve_fit.predict_quantile(X_test, q=0.975)
```

## Holt-Winters

```python
class HoltWinters(BaseEstimator):
    """Scikit-learn like interface for Holt-Winters method."""

    def __init__(self, season_len=24, alpha=0.5, beta=0.5, gamma=0.5):
        self.beta = beta
        self.alpha = alpha
        self.gamma = gamma
        self.season_len = season_len

    def fit(self, series):
        # note that unlike scikit-learn's fit method, it doesn't learn
        # the optimal model paramters, alpha, beta, gamma instead it takes
        # whatever the value the user specified the produces the predicted time
        # series, this of course can be changed.
        beta = self.beta
        alpha = self.alpha
        gamma = self.gamma
        season_len = self.season_len
        seasonals = self._initial_seasonal(series)

        # initial values
        predictions = []
        smooth = series[0]
        trend = self._initial_trend(series)
        predictions.append(smooth)

        for i in range(1, len(series)):
            value = series[i]
            previous_smooth = smooth
            seasonal = seasonals[i % season_len]
            smooth = alpha * (value - seasonal) + (1 - alpha) * (previous_smooth + trend)
            trend = beta * (smooth - previous_smooth) + (1 - beta) * trend
            seasonals[i % season_len] = gamma * (value - smooth) + (1 - gamma) * seasonal
            predictions.append(smooth + trend + seasonals[i % season_len])

        self.trend_ = trend
        self.smooth_ = smooth
        self.seasonals_ = seasonals
        self.predictions_ = predictions
        return self
    
    def _initial_trend(self, series):
        season_len = self.season_len
        total = 0.0
        for i in range(season_len):
            total += (series[i + season_len] - series[i]) / season_len

        trend = total / season_len
        return trend

    def _initial_seasonal(self, series):
        season_len = self.season_len
        n_seasons = len(series) // season_len

        season_averages = np.zeros(n_seasons)
        for j in range(n_seasons):
            start_index = season_len * j
            end_index = start_index + season_len
            season_average = np.sum(series[start_index:end_index]) / season_len
            season_averages[j] = season_average

        seasonals = np.zeros(season_len)
        seasons = np.arange(n_seasons)
        index = seasons * season_len
        for i in range(season_len):
            seasonal = np.sum(series[index + i] - season_averages) / n_seasons
            seasonals[i] = seasonal

        return seasonals

    def predict(self, n_preds=10):
        """
        Parameters
        ----------
        n_preds: int, default 10
            Predictions horizon. e.g. If the original input time series to the .fit
            method has a length of 50, then specifying n_preds = 10, will generate
            predictions for the next 10 steps. Resulting in a prediction length of 60.
        """
        predictions = self.predictions_
        original_series_len = len(predictions)
        for i in range(original_series_len, original_series_len + n_preds):
            m = i - original_series_len + 1
            prediction = self.smooth_ + m * self.trend_ + self.seasonals_[i % self.season_len]
            predictions.append(prediction)

        return predictions
```

```python
def timeseries_cv_score(params, series, loss_function, season_len=24, n_splits=3):
    """
    Iterating over folds, train model on each fold's training set,
    forecast and calculate error on each fold's test set.
    """
    errors = []    
    alpha, beta, gamma = params
    time_series_split = TimeSeriesSplit(n_splits=n_splits) 

    for train, test in time_series_split.split(series):
        model = HoltWinters(season_len, alpha, beta, gamma)
        model.fit(series[train])

        # evaluate the prediction on the test set only
        predictions = model.predict(n_preds=len(test))
        test_predictions = predictions[-len(test):]
        test_actual = series[test]
        error = loss_function(test_actual, test_predictions)
        errors.append(error)

    return np.mean(errors)
```

```python
x = [0, 0, 0]
test_size = 20
data = series.values[:-test_size]
opt = minimize(
  timeseries_cv_score,
  x0=x, 
  args=(data, mean_squared_log_error),
  method='TNC',
  bounds=((0, 1), (0, 1), (0, 1))
)
```

```python
alpha_final, beta_final, gamma_final = opt.x

model = HoltWinters(season_len, alpha_final, beta_final, gamma_final)
model.fit(data)
predictions = model.predict(n_preds=50)

print('original series length: ', len(series))
print('prediction length: ', len(predictions))
```

## Soft Labels/Label Smoothing

```python
from sklearn.base import BaseEstimator, ClassifierMixin
from sklearn.utils.validation import check_array
from scipy.special import softmax
import numpy as np

def _log_odds_ratio_scale(X):
    X = np.clip(X, 1e-8, 1 - 1e-8)   # numerical stability
    X = np.log(X / (1 - X))  # transform to log-odds-ratio space
    return X

class FuzzyTargetClassifier(ClassifierMixin, BaseEstimator):
        
    def __init__(self, regressor):
        '''
        Fits regressor in the log odds ratio space (inverse crossentropy) of target variable.
        during transform, rescales back to probability space with softmax function
        
        Parameters
        ---------
        regressor: Sklearn Regressor
            base regressor to fit log odds ratio space. Any valid sklearn regressor can be used here.
        
        '''
        
        self.regressor = regressor
        return
    
    def fit(self, X, y=None, **kwargs):
        #ensure passed y is onehotencoded-like
        y = check_array(y, accept_sparse=True, dtype = 'numeric', ensure_min_features=1)
        self.regressors_ = [clone(self.regressor) for _ in range(y.shape[1])]
        for i in range(y.shape[1]):
            self._fit_single_regressor(self.regressors_[i], X, y[:,i], **kwargs)
        
        return self
    
    def _fit_single_regressor(self, regressor, X, ysub, **kwargs):
        ysub = _log_odds_ratio_scale(ysub)        
        regressor.fit(X, ysub, **kwargs)
        return regressor    
        
    def decision_function(self,X):
        all_results = []
        for reg in self.regressors_:
            results = reg.predict(X)
            if results.ndim < 2:
                results = results.reshape(-1,1)
            all_results.append(results)
        
        results = np.hstack(all_results)                
        return results
    
    def predict_proba(self, X):
        results = self.decision_function(X)
        results = softmax(results, axis = 1)
        return results
    
    def predict(self, X):
        results = self.decision_function(X)
        results = results.argmax(1)
        return results
```

## Tree-Based Proximity

Random Forest Proximity

```python
class TreeBasedProximity(): # BaseEstimator, TransformerMixin
  """
  Create Proximity matrix

  normalization_type = column_wise  : Normalize columns to sum to 1
  normalization_type = n_trees      : pm / n_trees
  """

  def __init__(self, estimator, **init_params):
    self.estimator = estimator

    self.supported_types = {
      "RandomForest": "a",
      "XGBoost": "a",
      "GradientBoosting": "b"
    }
    
    for m, t in self.supported_types.items():
      if m in self.estimator.__class__.__name__:
        self.estimator_type = t
        break
    else:
        return Exception("Unsupported estimator")
    
    self.n_trees = len(self.estimator.estimators_)

  def fit(self, X, y=None, **fit_params):
    # Get leaf_indices indices with shape = (x.shape[0], n_trees)
    if self.estimator_type == "a":
      leaf_indices = self.estimator.apply(X)
    elif self.estimator_type == "b":
      leaf_indices = np.array([tree[0].apply(X) for tree in self.estimator.estimators_]).T

    self.pm_ = (
        (leaf_indices[:, None, :] == leaf_indices[None, :, :])
        .sum(axis=-1)
    )
    #np.fill_diagonal(self.pm_, 0)
    return self

  def normalize_(self, pm, normalization="n_trees", ):
    if normalization == "n_trees":
        divisor = self.n_trees
    elif normalization == "col_wise":
        divisor = pm.sum(axis=0, keepdims=True)
    else:
        return Exception("Invalid normalization type")
    return pm / divisor

  def transform(self, X=None, y=None, metric="similarity", normalization="n_trees", ):
    pm = self.normalize_(
        self.pm_,
        normalization
    )
    np.fill_diagonal(pm, 1)
    
    if metric=="distance":
      pm = 1-pm
    
    return pm
```

```python
model = RandomForestClassifier( # or Regressor
	n_estimators=100,
    max_depth=5, # make sure not too deep
    n_jobs=-1,
)
model.fit(X, y)

rfp = TreeBasedProximity(model) # randomforest model
rfp.fit(X)
rfp.transform(metric="similarity", normalization="n_trees")
```

## Pairwise Mutual Information Matrix

```python
from joblib import Parallel, delayed

class PairwiseMutualInformation():
  def __init__(self, normalized=True, n_bins=None, sample=None, random_state=None, n_jobs=None, ):
    self.n_bins = n_bins
    self.sample = sample
    self.normalized = normalized
    self.random_state = random_state
    self.n_jobs = n_jobs if n_jobs is not None else 1

  def compute_histogram2d(self, i, j, X, n_bins):
        return np.histogram2d(X[:, i], X[:, j], bins=n_bins)[0]

  def joint_entropies(self, X):
    histograms2d = np.empty((self.n_variables, self.n_variables, self.n_bins, self.n_bins))

    results = (
        Parallel(n_jobs=self.n_jobs)
        (
            delayed(self.compute_histogram2d)
            (i, j, X, self.n_bins)
            for i in range(self.n_variables)
            for j in range(self.n_variables)
        )
    )

    index = 0
    for i in range(self.n_variables):
        for j in range(self.n_variables):
            histograms2d[i, j] = results[index]
            index += 1

    probs = histograms2d / len(X) + 1e-100
    joint_entropies = -(probs * np.log2(probs)).sum((2,3))
    return joint_entropies

  def get_mutual_info_matrix(self, X):
    j_entropies = self.joint_entropies(X)
    entropies = j_entropies.diagonal()
    entropies_tile = np.tile(entropies, (self.n_variables, 1))
    sum_entropies = entropies_tile + entropies_tile.T

    mi_matrix = sum_entropies - j_entropies
    if self.normalized:
        mi_matrix = mi_matrix * 2 / sum_entropies
    return mi_matrix

  def fit(self, X, y=None):
    self.columns_ = X.columns

    if self.sample is not None:
      if type(self.sample) == int:
        X = df.sample(n=self.sample, random_state=self.random_state)
      elif type(self.sample) == float:
        X = df.sample(frac=self.sample, random_state=self.random_state)
      else:
        pass

    X = X.to_numpy()

    self.n_variables = X.shape[-1]
    self.n_samples = X.shape[0]

    if self.n_bins == None:
        self.n_bins = int((self.n_samples/5)**.5)

    self.mi_matrix_ = self.get_mutual_info_matrix(X)
    return self

  def transform(self, X, y=None):
    return pd.DataFrame(self.mi_matrix_, index=self.columns_, columns=self.columns_)

  def fit_transform(self, X, y=None):
    return self.fit(X, y).transform(X, y)
```

```python
matrix_similarity = PairwiseMutualInformation(normalized=True, n_jobs=-1, sample=0.10, random_state=0).fit_transform(df)
```


---

https://github.com/jmerilinna/education/blob/master/learning/associationmethodcomparison.ipynb

```python
# Install the necessary libraries
!pip install -q matplotlib pandas scipy scikit-learn numpy dcor

import matplotlib.pylab as plt
import numpy as np
import pandas as pd
from scipy.spatial.distance import squareform
from scipy.cluster.hierarchy import dendrogram, linkage
from sklearn.feature_selection import mutual_info_regression
import dcor

def plot_dendrogram(correlation_matrix, ax, title='Dendrogram', min_distance=0.0, method='ward'):
    """
    Plot a dendrogram based on a correlation matrix using hierarchical clustering.
    
    Parameters:
        correlation_matrix (pd.DataFrame): Correlation matrix with numerical values.
        ax (matplotlib.axes.Axes): Axis to plot the dendrogram on.
        title (str): Title for the dendrogram plot. Default is 'Dendrogram'.
        min_distance (float): Minimum distance threshold to adjust the linkage distances.
        method (str): Linkage method used for clustering. Default is 'ward'.
    
    Returns:
        None
    """
    # Convert correlation to distance (1 - absolute correlation)
    distance_matrix = 1 - correlation_matrix.abs()
    
    # Set diagonal elements to 0 (distance to self is 0)
    np.fill_diagonal(distance_matrix.values, 0)

    # Convert the distance matrix to condensed form
    condensed_distances = squareform(distance_matrix)

    # Perform hierarchical clustering
    linkage_matrix = linkage(condensed_distances, method=method)

    # Apply minimum distance threshold
    linkage_matrix[:, 2] = np.maximum(linkage_matrix[:, 2], min_distance)

    # Plot the dendrogram on the provided axis
    dendro = dendrogram(linkage_matrix,
                        labels=distance_matrix.columns,
                        leaf_font_size=6,
                        ax=ax)

    # Annotate the dendrogram with distance values
    for i, d in zip(dendro['icoord'], dendro['dcoord']):
        ax.text((i[1] + i[2]) / 2, d[1], f'{d[1]:.2f}', va='center', ha='right', fontsize=8)

    # Set plot title and labels
    ax.set_title(title)
    ax.set_xlabel("Features")
    ax.set_ylabel("Distance")

def distance_correlation_matrix(data):
    """
    Calculate the distance correlation between all pairs of columns in a pandas DataFrame.

    Distance correlation is a measure of statistical dependence between two features.
    It can detect both linear and non-linear relationships.

    Parameters:
        data (pd.DataFrame): Input data with numerical columns. Each column represents a feature.

    Returns:
        pd.DataFrame: A symmetric matrix of distance correlation scores for each pair of columns.
                      The diagonal elements represent the distance correlation of a column with itself.
    """
    # Ensure input consists of numeric columns only
    data = data.select_dtypes(include=[np.number])

    n = data.shape[1]  # Number of features (columns)
    columns = data.columns  # Column names
    dc_matrix = np.zeros((n, n))  # Initialize the distance correlation matrix

    # Compute distance correlation for each pair of features (i, j)
    for i in range(n):
        for j in range(i, n):  # Compute only the upper triangular part of the matrix
            dc = dcor.distance_correlation(data.iloc[:, i], data.iloc[:, j])

            # Fill the symmetric matrix with the computed distance correlation
            dc_matrix[i, j] = dc
            dc_matrix[j, i] = dc  # Matrix is symmetric

    # Convert the matrix to a pandas DataFrame for readability
    dc_df = pd.DataFrame(dc_matrix, index=columns, columns=columns)
    return dc_df

def mutual_information_matrix(data):
    """
    Calculate the normalized mutual information between all pairs of columns in a pandas DataFrame.

    Mutual information measures the dependency between two features and can detect both linear
    and non-linear monotonic relationships. The values are normalized to a range between 0 and 1.

    Parameters:
        data (pd.DataFrame): Input data with numerical columns. Each column represents a feature.

    Returns:
        pd.DataFrame: A symmetric matrix of normalized mutual information scores (range: 0 to 1).
                      The diagonal elements represent the mutual information of a column with itself.
    """
    # Ensure input consists of numeric columns only
    data = data.select_dtypes(include=[np.number])

    n = data.shape[1]  # Number of features (columns)
    columns = data.columns  # Column names
    mi_matrix = np.zeros((n, n))  # Initialize the mutual information matrix

    # Compute mutual information for each pair of features (i, j)
    for i in range(n):
        for j in range(i, n):  # Compute only the upper triangular part of the matrix
            x = data.iloc[:, i].values.reshape(-1, 1)
            y = data.iloc[:, j].values

            if i == j:
                # Self-mutual information (highest value for a single feature)
                mi = mutual_info_regression(x, x.ravel())[0]
            else:
                # Compute mutual information between different features
                mi = mutual_info_regression(x, y)[0]

            # Fill the symmetric matrix with the computed mutual information
            mi_matrix[i, j] = mi
            mi_matrix[j, i] = mi  # Matrix is symmetric

    # Convert the matrix to a pandas DataFrame for readability
    mi_df = pd.DataFrame(mi_matrix, index=columns, columns=columns)

    # Normalize the matrix so the diagonal values are 1 (self-correlation)
    mi_df /= mi_df.max().max()

    return mi_df
```

---


## Gradient Regularization

```python
import numpy as np

def linear_regression_with_gradient_regularization(X, y, lambda_ridge, lambda_second_gradient):
    """
    Perform linear regression with gradient regularization.
    
    Parameters:
    X (np.array): Design matrix of shape (n_samples, n_features)
    y (np.array): Target vector of shape (n_samples,)
    lambda_ridge (float): Regularization parameter
    lambda_second_gradient (float): Regularization parameter
    
    Returns:
    beta (np.array): Coefficient vector
    loss (float): Total loss including regularization
    """
    
    # Compute X^T X
    XTX = X.T @ X
    
    # Compute (X^T X)^2
    XTX_squared = XTX @ XTX
    
    # Compute the regularized matrix
    reg_ridge = lambda_ridge * np.eye(n_features)
    reg_second_gradient = 4 * lambda_second_gradient * XTX_squared
    regularized_matrix = XTX + reg_ridge + reg_second_gradient
    
    # Compute X^T y
    XTy = X.T @ y
    
    # Compute the closed-form solution
    beta = np.linalg.solve(regularized_matrix, XTy)
    
    # Compute the loss
    residuals = y - X @ beta
    mse = np.mean(residuals**2)
    reg_term = lambda_ridge * np.sum(beta**2) + lambda_second_gradient * np.sum(XTX**2)
    total_loss = mse + reg_term
    
    return beta, total_loss

# Example usage
np.random.seed(42)
n_samples, n_features = 100, 5
X = np.random.randn(n_samples, n_features)
true_beta = np.array([1, 2, 3, 4, 5])
y = X @ true_beta + np.random.randn(n_samples) * 0.1

beta_hat, loss = linear_regression_with_gradient_regularization(X, y, lambda_ridge=1, lambda_second_gradient=1e-4)

print("Estimated coefficients:", beta_hat)
print("Total loss:", loss)

# Compare with OLS
beta_ols = np.linalg.solve(X.T @ X, X.T @ y)
print("\nOLS coefficients:", beta_ols)

```

## Non-Linear Confidence Intervals

```python
def nlpredict(X, y, model, loss, popt, xnew, alpha=0.05, ub=1e-5, ef=1.05):
    """Prediction error for a nonlinear fit.

    Parameters
    ----------
    model : model function with signature model(x, ...)
    loss : loss function the model was fitted with loss(...)
    popt : the optimized paramters
    xnew : x-values to predict at
    alpha : confidence level, 95% = 0.05
    ub : upper bound for smallest allowed Hessian eigenvalue
    ef : eigenvalue factor for scaling Hessian

    This function uses numdifftools for the Hessian and Jacobian.

    Returns
    -------
    y, yint, se

    y : predicted values
    yint : prediction interval at alpha confidence interval
    se : standard error of prediction
    """
    ypred = model(xnew, *popt)

    hessp = nd.Hessian(lambda p: loss(*p))(popt)
    # for making the Hessian better conditioned.
    eps = max(ub, ef * np.linalg.eigvals(hessp).min())

    sse = loss(*popt)
    n = len(y)
    mse = sse / n
    I_fisher = np.linalg.pinv(hessp + np.eye(len(popt)) * eps)

    gprime = nd.Jacobian(lambda p: model(xnew, *p))(popt)
    
    temp = np.diag(gprime @ I_fisher @ gprime.T)
    if interval_type == "confidence":
	    pass
	elif interval_type == "prediction":
		# 1 + comes for the prediction interval, not for confidence interval
	    # https://online.stat.psu.edu/stat501/lesson/7/7.2
		temp += 1
	
    sigmas = np.sqrt(
	    mse *
	    (1 + temp)
    )
    
    tval = t.ppf(1 - alpha / 2, len(y) - len(popt))

    return [
        ypred,
        np.array(
            [
                ypred + tval * sigmas,
                ypred - tval * sigmas,
            ]
        ).T,
        sigmas,
    ]
```