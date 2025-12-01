# Generative AI

## LLM

Large Language Models

### Limitations

- Bias
- Hallucinations
- Expensive to build & run

### ChatGPT

1. Train supervised policy
   1. Provide prompt
   2. Labeler demonstrates desired output behavior
   3. Fine-tune model
2. Collect comparaison data & train reward model
   1. Prompt and several model outputs are samples
   2. Labeler ranks outputs from best to worst
   3. Data used to train reward model
3. Policy optimization

## Fine-Tuning

Process of training model using specific data, usually with a significantly smaller learning rate

### Disadvantages

- requires copy of the model
- associated costs of hosting it
- Risk of “catastrophic forgetting”: model forgets previously learnt information

## RAG

Retrieval Augmented Generation

Makes use of a source of knowledge, usually vector store of embeddings and associated texts

By comparing predicted embeddings of query to embeddings in the vector store, we can form a prompt for the LLM that fits inside its context and contains the information needed to answer the question

### Advantages

- Does not require re-training
  - No need to deal with internal workings of model
  - Just adjust the data that the model “cheats” off
- Reduces the amount a model “hallucinates”

### Difficulties

- Finding relevant data to give the model

### Keywords

- Data organization: 
- Vector creation: Unique index that points right to a chunk of information
- Querying: Prompting
- Retrieval
  - Prompt goes through embedding model and transforms into a vector
  - Systems uses this to get the chunks most relevant to the question
- Prepending the context: Most relevant chunks are served up as context
- Answer generation

![image-20240527161317627](./assets/image-20240527161317627.png){ loading=lazy }

![image-20240527161350830](./assets/image-20240527161350830.png){ loading=lazy }

### Types of Questions

|      |                                                              |                                                              |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ |
|      | No special skills required to answer<br />Just need right reference material | What is the capital of France?                               |
|      |                                                              | Write a poem in German<br />Write a computer program to calculate the first n natural numbers |
