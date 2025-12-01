---
title: "Pandoc Beamer"
author: "Federico Tartarini"
institute: "My institute"
theme: "metropolis"
fonttheme: "default"
fontsize: 11pt
urlcolor: red
linkstyle: bold
aspectratio: 169
logo: images/pandoc-small.png
date: 01/10/2021
section-titles: false
toc: false
---

# General information

## Themes, fonts, etc.

- This presentation is made with **Metropolis** theme.

## Links

- Matrix of [beamer themes](https://hartwork.org/beamer-theme-matrix/)
- Font themes: [http://www.deic.uab.es/~iblanes/beamer_gallery/index_by_font.html](http://www.deic.uab.es/~iblanes/beamer_gallery/index_by_font.html)

# Formatting

## Text formatting

Normal text.
*Italic text* and **bold text**.
~~Strike out~~ is supported.

## Notes

> This is a note.
> > Nested notes are not supported.
> And it continues.

## Blocks

### This is a block A

- Line A
- Line B

### 

New block without header.

### This is a block B.

- Line C
- Line D

## Listings

Listings out of the block.

```sh
#!/bin/bash
echo "Hello world!"
echo "line"
```
### Listings in the block.

```python
print("Hello world!")
```

## Table

**Item** | **Description** | **Q-ty**
:--------|-----------------:|:---:
Item A | Item A description | 2
Item B | Item B description | 5
Item C | N/A | 100

## Single picture 

This is how we insert picture. Caption is produced automatically from the alt text.

```
![Aleph 0](images/pandoc.png){ loading=lazy } 
```

![Aleph 0](images/pandoc.png){ loading=lazy } 

## Two or more pictures in a raw

Here are two pictures in the raw. We can also change two pictures size (height or width).

###
```
![](images/pandoc.png){ loading=lazy }{height=10%}\ ![](images/pandoc.png){ loading=lazy }{height=30%} 
```

![](images/pandoc.png){ loading=lazy }{ height=10% }\ ![](images/pandoc.png){ loading=lazy }{ height=30% }

## Lists

1. Idea 1
2. Idea 2
	- genius idea A
	- more genius 2
3. Conclusion

## LaTeX

Hello, *world*^[My footnote].

 Some \textit{\LaTeX\ commands}.

And some $\sqrt{a^2 + b^2}$ math.

$\displaystyle\lim_{x \to \infty} x^2 = \infty$

## Two columns of equal width

::: columns

:::: column

Left column text.

Another text line.

::::

:::: column

- Item 1.
- Item 2.
- Item 3.

::::

:::

## Two columns of with 40:60 split

::: columns

:::: {.column width=40%}

Left column text.

Another text line.

::::

:::: {.column width=60%}

- Item 1.
- Item 2.
- Item 3.

::::

:::

## Three columns with 30:40:30 split

::: columns

:::: {.column width=30%}

Left column text.

Another text line.

::::

:::: {.column width=40%}

Middle column list:

1. Item 1.
2. Item 2.

::::

:::: {.column width=30%}

Right column list:

- Item 1.
- Item 2.

::::

:::

## Two columns: image and text

::: columns

:::: column

![](images/pandoc.png){ loading=lazy }{height=50%}

::::

:::: column

Text in the right column.  

List from the right column:

- Item 1.
- Item 2.
::::

:::

## Two columns: image and table

::: columns

:::: column

![](images/pandoc.png){ loading=lazy }{height=50%}

::::

:::: column

| **Item** | **Option** |
|:---------|:----------:|
| Item 1   | Option 1   |
| Item 2   | Option 2   |

::::

:::

# References

## Citations

As described in [@Tartarini2020a;@Tartarini2020]

Single reference [@Tartarini2020]

## Reference figures

Reference Figure \ref{first} and \ref{second} 

![This is the caption\label{first}](images/pandoc.png){ loading=lazy }{height=50%}

## Second figure

Reference Figure \ref{second} and 

![This is the caption\label{second}](images/pandoc.png){ loading=lazy }{height=50%}

## Fancy layout

### Proposal

- Point A
- Point B

::: columns

:::: column

### Pros

- Good
- Better
- Best

::::

:::: column

### Cons

- Bad
- Worse
- Worst

::::

:::

### Conclusion

- Let's go for it!
- No way we go for it!

## References

::: {#refs}
:::