# Advanced

## Uncertainty

```python
import numpy as np
import torch, torchvision
from torch.autograd import Variable, grad
import torch.distributions as td
import math
from torch.optim import Adam
import scipy.stats


x_data = torch.randn(100)+0.0 ## observed data (here sampled under H0)

N = x_data.shape[0] ## number of observations

mu_null = torch.zeros(1)
sigma_null_hat = Variable(torch.ones(1), requires_grad=True)

def log_lik(mu, sigma):
  return td.Normal(loc=mu, scale=sigma).log_prob(x_data).sum()

## Find theta_null_hat by some gradient descent algorithm (in this case an closed-form expression would be trivial to obtain (see below)):
opt = Adam([sigma_null_hat], lr=0.01)
for epoch in range(2000):
    opt.zero_grad() ## reset gradient accumulator or optimizer
    loss = - log_lik(mu_null, sigma_null_hat) ## compute log likelihood with current value of sigma_null_hat  (= Forward pass)
    loss.backward() ## compute gradients (= Backward pass)
    opt.step()      ## update sigma_null_hat

print(f'parameter fitted under null: sigma: {sigma_null_hat}, expected: {torch.sqrt((x_data**2).mean())}')
#> parameter fitted under null: sigma: tensor([0.9260], requires_grad=True), expected: 0.9259940385818481

theta_null_hat = (mu_null, sigma_null_hat)

U = torch.tensor(torch.autograd.functional.jacobian(log_lik, theta_null_hat)) ## Jacobian (= vector of partial derivatives of log likelihood w.r.t. the parameters (of the full/alternative model)) = score
I = -torch.tensor(torch.autograd.functional.hessian(log_lik, theta_null_hat)) / N ## estimate of the Fisher information matrix
S = torch.t(U) @ torch.inverse(I) @ U / N ## test statistic, often named "LM" (as in Lagrange multiplier), would be zero at the maximum likelihood estimate

pval_score_test = 1 - scipy.stats.chi2(df = 1).cdf(S) ## S asymptocially follows a chi^2 distribution with degrees of freedom equal to the number of parameters fixed under H0
print(f'p-value Chi^2-based score test: {pval_score_test}')
#> p-value Chi^2-based score test: 0.9203232752568568

## comparison with Student's t-test:
pval_t_test = scipy.stats.ttest_1samp(x_data, popmean = 0).pvalue
print(f'p-value Student\'s t-test: {pval_t_test}')
#> p-value Student's t-test: 0.9209265268946605
```

```python
## another example

env_loss = loss_fn(env_outputs, env_targets)
total_loss += env_loss
env_grads = torch.autograd.grad(env_loss, params, retain_graph=True, create_graph=True)

print(env_grads[0])
hess_params = torch.zeros_like(env_grads[0])
for i in range(env_grads[0].size(0)):
    for j in range(env_grads[0].size(1)):
        hess_params[i, j] = torch.autograd.grad(env_grads[0][i][j], params, retain_graph=True)[0][i, j] ##  <--- error here
print(hess_params)
```

## Early-Stopping

Class

```python
  import copy


  class EarlyStopping:
      def __init__(self, patience=5, min_delta=0, restore_best_weights=True):
          self.patience = patience
          self.min_delta = min_delta
          self.restore_best_weights = restore_best_weights
          self.best_model = None
          self.best_loss = None
          self.counter = 0
          self.status = ""

      def __call__(self, model, val_loss):
          if self.best_loss is None:
              self.best_loss = val_loss
              self.best_model = copy.deepcopy(model.state_dict())
          elif self.best_loss - val_loss >= self.min_delta:
              self.best_model = copy.deepcopy(model.state_dict())
              self.best_loss = val_loss
              self.counter = 0
              self.status = f"Improvement found, counter reset to {self.counter}"
          else:
              self.counter += 1
              self.status = f"No improvement in the last {self.counter} epochs"
              if self.counter >= self.patience:
                  self.status = f"Early stopping triggered after {self.counter} epochs."
                  if self.restore_best_weights:
                      model.load_state_dict(self.best_model)
                  return True
          return False
```

Classification

```python
  import time

  import numpy as np
  import pandas as pd
  import torch
  import tqdm
  from sklearn.metrics import accuracy_score
  from sklearn.model_selection import train_test_split
  from sklearn.preprocessing import LabelEncoder, StandardScaler
  from torch import nn
  from torch.autograd import Variable
  from torch.utils.data import DataLoader, TensorDataset

  ## Set random seed for reproducibility
  np.random.seed(42)
  torch.manual_seed(42)

  def load_data():
      df = pd.read_csv(
          "https://data.heatonresearch.com/data/t81-558/iris.csv", na_values=["NA", "?"]
      )

      le = LabelEncoder()

      x = df[["sepal_l", "sepal_w", "petal_l", "petal_w"]].values
      y = le.fit_transform(df["species"])
      species = le.classes_

      ## Split into validation and training sets
      x_train, x_test, y_train, y_test = train_test_split(
          x, y, test_size=0.25, random_state=42
      )

      scaler = StandardScaler()
      x_train = scaler.fit_transform(x_train)
      x_test = scaler.transform(x_test)

      ## Numpy to Torch Tensor
      x_train = torch.tensor(x_train, device=device, dtype=torch.float32)
      y_train = torch.tensor(y_train, device=device, dtype=torch.long)

      x_test = torch.tensor(x_test, device=device, dtype=torch.float32)
      y_test = torch.tensor(y_test, device=device, dtype=torch.long)

      return x_train, x_test, y_train, y_test, species


  x_train, x_test, y_train, y_test, species = load_data()

  ## Create datasets
  BATCH_SIZE = 16

  dataset_train = TensorDataset(x_train, y_train)
  dataloader_train = DataLoader(
      dataset_train, batch_size=BATCH_SIZE, shuffle=True)

  dataset_test = TensorDataset(x_test, y_test)
  dataloader_test = DataLoader(dataset_test, batch_size=BATCH_SIZE, shuffle=True)

  ## Create model using nn.Sequential
  model = nn.Sequential(
      nn.Linear(x_train.shape[1], 50),
      nn.ReLU(),
      nn.Linear(50, 25),
      nn.ReLU(),
      nn.Linear(25, len(species)),
      nn.LogSoftmax(dim=1),
  )

  model = torch.compile(model,backend="aot_eager").to(device)

  loss_fn = nn.CrossEntropyLoss()  ## cross entropy loss

  optimizer = torch.optim.Adam(model.parameters(), lr=0.01)
  es = EarlyStopping()

  epoch = 0
  done = False
  while epoch < 1000 and not done:
      epoch += 1
      steps = list(enumerate(dataloader_train))
      pbar = tqdm.tqdm(steps)
      model.train()
      for i, (x_batch, y_batch) in pbar:
          y_batch_pred = model(x_batch.to(device))
          loss = loss_fn(y_batch_pred, y_batch.to(device))
          optimizer.zero_grad(set_to_none=True)
          loss.backward()
          optimizer.step()

          loss, current = loss.detatch(), (i + 1) * len(x_batch)
          if i == len(steps) - 1:
              model.eval()
              with torch.inference_mode(): # turn off history tracking
	              pred = model(x_test)
	              vloss = loss_fn(pred, y_test)
              if es(model, vloss):
                  done = True
              pbar.set_description(
                  f"Epoch: {epoch}, tloss: {loss}, vloss: {vloss:>7f}, {es.status}"
              )
          else:
              pbar.set_description(f"Epoch: {epoch}, tloss {loss:}")
```

Regression

```python
  import time

  import numpy as np
  import pandas as pd
  import torch.nn as nn
  import torch.nn.functional as F
  import tqdm
  from sklearn import preprocessing
  from sklearn.metrics import accuracy_score
  from sklearn.model_selection import train_test_split
  from torch.autograd import Variable
  from torch.utils.data import DataLoader, TensorDataset

  ## Read the MPG dataset.
  df = pd.read_csv(
      "https://data.heatonresearch.com/data/t81-558/auto-mpg.csv", na_values=["NA", "?"]
  )

  cars = df["name"]

  ## Handle missing value
  df["horsepower"] = df["horsepower"].fillna(df["horsepower"].median())

  ## Pandas to Numpy
  x = df[
      [
          "cylinders",
          "displacement",
          "horsepower",
          "weight",
          "acceleration",
          "year",
          "origin",
      ]
  ].values
  y = df["mpg"].values  ## regression

  ## Split into validation and training sets
  x_train, x_test, y_train, y_test = train_test_split(
      x, y, test_size=0.25, random_state=42
  )

  ## Numpy to Torch Tensor
  x_train = torch.tensor(x_train, device=device, dtype=torch.float32)
  y_train = torch.tensor(y_train, device=device, dtype=torch.float32)

  x_test = torch.tensor(x_test, device=device, dtype=torch.float32)
  y_test = torch.tensor(y_test, device=device, dtype=torch.float32)


  ## Create datasets
  BATCH_SIZE = 16

  dataset_train = TensorDataset(x_train, y_train)
  dataloader_train = DataLoader(dataset_train, batch_size=BATCH_SIZE, shuffle=True)

  dataset_test = TensorDataset(x_test, y_test)
  dataloader_test = DataLoader(dataset_test, batch_size=BATCH_SIZE, shuffle=True)


  ## Create model

  model = nn.Sequential(
      nn.Linear(x_train.shape[1], 50), 
      nn.ReLU(), 
      nn.Linear(50, 25), 
      nn.ReLU(), 
      nn.Linear(25, 1)
  )

  model = torch.compile(model, backend="aot_eager").to(device)

  ## Define the loss function for regression
  loss_fn = nn.MSELoss()

  ## Define the optimizer
  optimizer = torch.optim.Adam(model.parameters(), lr=0.01)

  es = EarlyStopping()

  epoch = 0
  done = False
  while epoch < 1000 and not done:
      epoch += 1
      steps = list(enumerate(dataloader_train))
      pbar = tqdm.tqdm(steps)
      model.train()
      for i, (x_batch, y_batch) in pbar:
          y_batch_pred = model(x_batch).flatten()  #
          loss = loss_fn(y_batch_pred, y_batch)
          optimizer.zero_grad(set_to_none=True)
          loss.backward()
          optimizer.step()

          loss, current = loss.detatch(), (i + 1) * len(x_batch)
          if i == len(steps) - 1:
              model.eval()
          
	          with torch.inference_mode(): # turn off history tracking
	              pred = model(x_test).flatten()
	              vloss = loss_fn(pred, y_test)
              if es(model, vloss):
                  done = True
              pbar.set_description(
                  f"Epoch: {epoch}, tloss: {loss}, vloss: {vloss:>7f}, EStop:[{es.status}]"
              )
          else:
              pbar.set_description(f"Epoch: {epoch}, tloss {loss:}")
```

## Dropout

```python
## p = p_drop; NOT p_keep like Tensorflow
model = nn.Sequential(
  nn.Dropout(p=0.2),
  ## ...,
  nn.Dropout(p=0.2),
  ## ...,
)

## make sure to specify model.train() and model.eval(), as dropout processing is only required for "building" the network. no need to processing for evaluation
```

## Quantization

```python
## direct precision reduction
model = model.half() ## changes everything from float32 to float16

## dynamic precision reduction
model = torch.quantization.quantize_dynamic(
  model,
  {torch.nn.Linear},
  dtype=torch.qint8
)

## static precision reduction
## very complicated ngl
```

## Constrained Optimization

Warning: this clamping is not communicated to the optimizer, and in particular destroys the gradients. So the optimizer falsely believes that it has moved the parameter in a certain direction, when in fact it is clamped to the same value as before.

```python
opt = optim.SGD(model.parameters(), lr=0.1)
for i in range(1000):
    out = model(inputs)
    loss = loss_fn(out, labels)
    print(i, loss.detatch())
    opt.zero_grad()
    loss.backward()
    opt.step()
    
    # enforce the constraint that the weights fall in the range (-1, 1)
    with torch.no_grad():
        for param in model.parameters():
            param.clamp_(-1, 1)
```

## Ensembling

### Approach 1

```python
class MyEnsemble(nn.Module):
    def __init__(self, modelA, modelB, nb_classes=10):
        super(MyEnsemble, self).__init__()
        self.modelA = modelA
        self.modelB = modelB
        # Remove last linear layer
        self.modelA.fc = nn.Identity()
        self.modelB.fc = nn.Identity()
        
        # Create new classifier
        self.classifier = nn.Linear(2048+512, nb_classes)
        
    def forward(self, x):
        x1 = self.modelA(x.clone())  # clone to make sure x is not changed by inplace methods
        x1 = x1.view(x1.size(0), -1)
        x2 = self.modelB(x)
        x2 = x2.view(x2.size(0), -1)
        x = torch.cat((x1, x2), dim=1)
        
        x = self.classifier(F.relu(x))
        return x

# Train your separate models
# ...
# We use pretrained torchvision models here
modelA = models.resnet50(pretrained=True)
modelB = models.resnet18(pretrained=True)

# Freeze these models
for param in modelA.parameters():
    param.requires_grad_(False)

for param in modelB.parameters():
    param.requires_grad_(False)

# Create ensemble model
model = MyEnsemble(modelA, modelB)
x = torch.randn(1, 3, 224, 224)
output = model(x)
```

### Approach 2

When you create an ensemble in PyTorch, it's better to use the `nn.ModuleList()` class from PyTorch. The `nn.ModuleList()` has the same functions as a normal Python list like `append()`. When you create an Ensemble Model like this, you can directly call the `backward` operations and the gradient descent will occur through the model.

Below is an ensemble Neural Network (`EnsembleNet`) that uses the `NeuralNet` as individual NN instances for the ensemble.

To use bagging, simply create an X_input_list where the different elements of the list are Tensors that have been sampled with replacement from your training data. (Your X_input_list and the num_ensemble must be of the same size)

You can modify the `EnsembleNet` initialization code to take a list of different neural networks as well.

```python
class NeuralNet(nn.Module):
  def __init__(self):
    super(NeuralNet, self).__init__()
    self.fc1 = nn.Linear(in_dim, out_dim)
    self.fc2 = nn.Linear(out_dim, 1)


  def forward(self, X):
    """ X must be of shape [-1, in_dim]"""
    X = self.fc1(X)
    return torch.sigmoid(self.fc2(X))


class EnsembleNet(nn.Module):
  def __init__(self, net = NeuralNet, num_ensemble=5, seed_val=SEED):
      super(EnsembleNet, self).__init__()
      self.ensembles = nn.ModuleList()

      for i in range(num_ensemble):
          torch.manual_seed(seed_val*i+1)
          if torch.cuda.is_available(): # To randomize init of NNs for Ensembles
              torch.cuda.manual_seed(seed_val*i+1)
          self.ensembles.append(net)

      self.final = nn.Linear(num_ensemble, 1)

  def forward(self, X_in_list):
      pred = torch.cat([net(X_in_list[i]) for i,net in enumerate(self.ensembles)])
      pred = pred.reshape(-1, len(self.ensembles))
      return torch.sigmoid(self.final(pred))
```

## Sklearn Integration

```python
from skorch import *

model = NeuralNetRegressor(
  Network,
  max_epochs=100,
  lr=0.001,
  verbose=1
)

model = NeuralNetClassifier(
  Network,
  max_epochs=10,
  lr=0.1,
  # Shuffle training data on each epoch
  iterator_train__shuffle=True,
)
```

```python
model.fit(X, y)
pred = model.predict(X)
pred_proba = model.predict_proba(X)
```

### Hyperparameter Tuning

```python
from sklearn.model_selection import GridSearchCV

params = {
    'lr': [0.001,0.005, 0.01, 0.05, 0.1, 0.2, 0.3],
    'max_epochs': list(range(500,5500, 500))
}

gs = GridSearchCV(model, params, refit=False, scoring='r2', verbose=1, cv=10)

gs.fit(X_trf, y_trf)
```

####  Latin Hypercube Sampling

```python
from scipy.stats import qmc, norm, loguniform, randint, uniform
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import cross_val_score
from joblib import Parallel, delayed
import numpy as np

# Example: param distributions
param_distributions = {
    # Use discrete loguniform for e.g. learning rate
    'n_estimators': randint(50, 500+1),               # Discrete uniform integer
    'max_depth': randint(2, 21),                      # Discrete uniform integer
    'min_samples_split': randint(2, 11),              # Discrete uniform integer
    # 'max_features': uniform(0.3, 0.7),              # Uniform float [0.3, 1.0] if wanted
    # 'reg_param': loguniform(1e-5, 1e-2)             # Log-uniform float if wanted
}
param_names = list(param_distributions.keys())
n_samples = 20
d = len(param_names)

sampler = qmc.LatinHypercube(d=d)
lhs_samples = sampler.random(n=n_samples)

# Transform LHS samples using inverse CDF ("ppf") for each distribution
param_grid = []
for row in lhs_samples:
    params = {}
    for i, name in enumerate(param_names):
        dist = param_distributions[name]
        val = dist.ppf(row[i])
        # Ensure int if using a discrete distribution
        if hasattr(dist, 'dist') and dist.dist.name == 'randint':
            val = int(val)
        params[name] = val
    param_grid.append(params)

# Vectorized model evaluation (same as before)
def eval_model(params):
    clf = RandomForestClassifier(**params)
    scores = cross_val_score(clf, X, y, cv=3, scoring='accuracy')
    return params, np.mean(scores)

X, y = ...  # your data here

results = Parallel(n_jobs=-1)(delayed(eval_model)(params) for params in param_grid)
best_params, best_score = max(results, key=lambda x: x[1])
print('Best params:', best_params)
print('Best CV score:', best_score)
```

## Batch Normalization

Flawed

- Training: No bessel correction for variance
- Inference: bessel correction for variance

## Gradient Regularization

```python
grads = torch.autograd.grad(
	loss,
	model.parameters(),
	retain_graph=True,
	create_graph=True
)
grads_norm_penalty = (
	torch.concat(
		[g.view(-1) for g in grads]
	)
	.norm(p=2)
)

lam = alpha = 1e-4
cost = loss + (lam * regularization) + (alpha * grads_norm_penalty)
cost.backward()
```

## Smooth F1 Loss

```python
import torch

class F1Loss:
	def __init__(self, padding = 1e-7):
		self.padding = padding
		
	def __call__(self, y_true, y_pred):
	    tp = torch.sum((y_true * y_pred).float(), dim=0)
	    tn = torch.sum(((1 - y_true) * (1 - y_pred)).float(), dim=0)
	    fp = torch.sum(((1 - y_true) * y_pred).float(), dim=0)
	    fn = torch.sum((y_true * (1 - y_pred)).float(), dim=0)
	
	    p = tp / (tp + fp + self.padding)
	    r = tp / (tp + fn + self.padding)

		s = tn / (tn + fp + self.padding)
	    n = tn / (tn + fn + self.padding)
	
	    #f1 = 2 * (p * r) / (p + r + 1e-7)
	    #f1 = torch.where(torch.isnan(f1), torch.zeros_like(f1), f1)
	    custom_f1 = 4 * (p * r * s * n) / (p + r + s + n + self.padding)
	    f1 = torch.where(torch.isnan(f1), torch.zeros_like(f1), f1)
	    
	    return 1 - torch.mean(f1)
```

## LogCosh

```python
class LogCoshLoss(torch.nn.Module):
	def _log_cosh(x: torch.Tensor) -> torch.Tensor:
	    return x + torch.nn.functional.softplus(-2. * x) - math.log(2.0) # math.log will be faster for a single number
	def log_cosh_loss(y_pred: torch.Tensor, y_true: torch.Tensor) -> torch.Tensor:
	    return torch.mean(_log_cosh(y_pred - y_true))

    def forward(
        self, y_pred: torch.Tensor, y_true: torch.Tensor
    ) -> torch.Tensor:
        return self.log_cosh_loss(y_pred, y_true)
```

## Tobit Regression

```python
def loglike(y, sigma, y_, up_ind, down_ind):
    """Calculate logarithm of likelihood for censored tobit model.
    Args:
        Model parameters:
            y: model output
            sigma: variance of random error (estimated during learning)
        True data:
            y_: observed data
            up_ind: boolean indication of right censoring
            down_ind: boolean indication of left censoring
    Returns:
        Logharithm of likelihood
    """

    normaldist = torch.distributions.Normal(
        zero_tensor, sigma)

    # model outputs normal distribution with center at y and std at sigma

    # probability function of normal distribution at point y_
    not_censored_log = normaldist.log_prob(y_ - y)
    # probability of point random variable being more than y_
    up_censored_log_argument = (1 - normaldist.cdf(y_ - y))
    # probability of random variable being less than y_
    down_censored_log_argument = normaldist.cdf(y_ - y)

    up_censored_log_argument = torch.clip(
        up_censored_log_argument, 0.00001, 0.99999)
    down_censored_log_argument = torch.clip(
        down_censored_log_argument, 0.00001, 0.99999)

    # logarithm of likelihood
    loglike = not_censored_log * (1 - up_ind) * (1 - down_ind)
    loglike += torch.log(up_censored_log_argument) * up_ind
    loglike += torch.log(down_censored_log_argument) * down_ind

    loglike2 = torch.sum(loglike)
    # we want to maximize likelihood, but optimizer minimizes by default
    loss = -loglike2
    return loss
```

## Getting the Mode of Log-Normal Distribution

```python
import torch
import torch.nn as nn
import torch.optim as optim

# Define a simple neural network to predict the parameters (mu and sigma) of the Log-Normal distribution
class LogNormalModeModel(nn.Module):
    def __init__(self):
        super(LogNormalModeModel, self).__init__()
        self.fc_mu = nn.Linear(1, 1)  # Predict mu
        self.fc_sigma = nn.Linear(1, 1)  # Predict sigma
    
    def forward(self, x):
        mu = self.fc_mu(x)
        sigma = torch.exp(self.fc_sigma(x))  # Ensure sigma is positive
        return mu, sigma


# Define the loss function as Negative Log-Likelihood for a Log-Normal distribution
def negative_log_likelihood_lognormal(y_true, mu, sigma):
    # Log-Normal log-likelihood: 
    # log(p(y)) = -0.5 * log(2 * pi) - log(sigma) - ((log(y) - mu) ** 2) / (2 * sigma^2)
    log_y = torch.log(y_true)
    nll = torch.mean(0.5 * torch.log(2 * torch.pi) + torch.log(sigma) + ((log_y - mu) ** 2) / (2 * sigma**2))
    return nll

optimizer = optim.AdamW(model.parameters(), lr=1e-9) # Define optimizer
model = LogNormalModeModel() # Instantiate the model

# Training loop
num_epochs = 500
for epoch in range(num_epochs):
    model.train()
    
    # Forward pass: predict mu and sigma
    mu, sigma = model(x)
    
    # Compute the negative log-likelihood
    loss = negative_log_likelihood_lognormal(y_true, mu, sigma)
    
    # Backward pass and optimization
    optimizer.zero_grad()
    loss.backward()
    optimizer.step()
    
    if epoch % 50 == 0:
        print(f'Epoch [{epoch}/{num_epochs}], Loss: {loss.item():.4f}')

# After training, the model will predict mu and sigma
model.eval()
with torch.no_grad():
    mu_pred, sigma_pred = model(x)
    # Calculate the mode for each predicted (mu, sigma) pair
    mode_pred = torch.exp(mu_pred - sigma_pred**2)
    ```

## Differentiable MAD

```python
import torch

def soft_median(x, alpha=1.0):
    """
    Compute the differentiable soft median (approximation of median).
    
    Parameters:
        x (Tensor): Input tensor of shape (N,) or (batch_size, N).
        alpha (float): Controls the "softness" of the median, higher alpha makes it closer to the true median.
    
    Returns:
        Tensor: Soft median of the input tensor.
    """
    sorted_x, _ = torch.sort(x, dim=-1)
    n = x.size(-1)
    
    # SoftMedian is the weighted average based on the sorted tensor
    indices = torch.arange(n, dtype=torch.float32, device=x.device).unsqueeze(0)  # (1, N)
    weights = torch.exp(-alpha * torch.abs(indices - (n - 1) / 2.0))  # weight decays based on distance from center
    weights = weights / weights.sum(-1, keepdim=True)  # Normalize weights
    
    soft_median = torch.sum(sorted_x * weights, dim=-1)
    return soft_median

def soft_mad(x, alpha=1.0):
    """
    Compute the SoftMedian Absolute Deviation (SoftMAD) using SoftMedian.
    
    Parameters:
        x (Tensor): Input tensor of shape (N,) or (batch_size, N).
        alpha (float): Softness factor for the soft median.
    
    Returns:
        Tensor: SoftMedian Absolute Deviation of the input tensor.
    """
    median = soft_median(x, alpha)
    deviation = torch.abs(x - median)
    mad = soft_median(deviation, alpha)
    return mad

# Example usage:
x = torch.randn(10)  # Random tensor of shape (10,)
soft_mad_value = soft_mad(x, alpha=1.0)
print("Soft Median Absolute Deviation:", soft_mad_value)
```