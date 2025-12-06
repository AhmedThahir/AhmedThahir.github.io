## Validation

- Fan-out?
	- Ideally, all these 3 should be equal
		- `COUNT(DISTINCT primary_key)`
		- `COUNT(primary_key)`
		- `COUNT(1)`
- Numbers?
	- Decompose binary outcomes into components and verify if they add up