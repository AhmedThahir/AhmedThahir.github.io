## `select`

```mysql
## Display values of table
select * from tableName;
select * from tableName where id = 11;

select name, salary/12 as monthlySalary from tableName;

## unique
select distinct city from table;
select count(distinct city) from table;
```

## Subqueries

```mysql
age + salary
age - salary
age * salary
age / salary
```

## Clauses

connectives

### logical

```mysql
and
or
not
```

### `as`

```mysql
select name, courseId
	from instructors as i, teaches as t
```

### `where`

```mysql
where id = 11;
where Student.instructor = Teacher.name;
```

### `like`

for string operations

```mysql
where name like "a%"; ## no character/any number of characters
where name like "a_"; ## 1 character

## we can create our own escape characters
where name like "100\%" escape '\';
where name like "100&%" escape '&';
```

### `having`

for group by clause

```mysql
group by age having name like "a%";
```

### `between`

inclusive on both sides

```mysql
select * from Student
	where age between 15 and 20 ## range is [15, 20]
```

## Operations

### Merge

```mysql
select * from students, players
	where students.id = players.id;
	
select name, courseId
	from instructors as i, teaches as t
	where i.id = t.id;
	
select * from Student
	where (age, name) = (15, "Thahir")
```

### Cartesian Product

```mysql
select * from students, teachers;
```

For every record of `students`, there will be every possible combination with `teachers`

```mysql
select * from teachers, students;
```

For every record of `teachers`, there will be every possible combination with `students`

### Ordering

```mysql
select name from instructor order by name asc;
select name from instructor order by name desc;
```

if 2 people have the same name, the 2nd condition (here, age) will be given priority

```mysql
select name from instructor order by name desc, age desc;
```

## Calculus

TRC = Tuple Relation Calculus

DRC = Domain Relation Calculus

### Semantic Representation

```mysql
select A1, A2, ..., Am
	from R1, R2, ..., Rn
	where P
```

### Mathematical Representation

$$
\Pi_{A_1, A_2, \dots, A_m}
\bigg(
\sigma_{P}
\Big(
R_1 \times R_2 \times \dots \times R_n
\Big)
\bigg)
$$

|         Symbol         |            Meaning            |
| :--------------------: | :---------------------------: |
|         $\Pi$          |          Projection           |
| $A_1, A_2, \dots, A_m$ |     Attributes (Columns)      |
|        $\sigma$        |           Selection           |
|          $P$           | Predicate (`where` condition) |
| $R_1, R_2, \dots, R_n$ |           Relations           |

## Set Operations

|  Operation  |             |   Meaning   |
| :---------: | :---------: | :---------: |
|   `union`   |   a or b    | $A \cup B$  |
| `intersect` |   a and b   | $A \cap B$  |
|  `except`   | a but not b | $A \cap B'$ |

```mysql
select cno from courses
	where age

(select id from Student where age >= 15)
except
(select id from Student where age < 20);

## equivalent of 
select * from Student where age between 15 and 20;
```

## Logic

- True (1)
- False (0)
- Unknown (X - don’t care)

Any comparison with `null` gives unknown
for eg,

- `age > null`
- `age <> null`
- `age = null`

## Aggregate Functions

```mysql
count(*)
count(city)
count(distinct city)

max(salary)
min(salary)

sum(salary)
avg(salary)
```

```mysql
select sum(salary)
	from Teachers;

select dept, sum(salary)
	from Teachers
	where age > 25
	group by dept
	having avg(salary) > 45000;
```

The grouping attribute must be displayed as well, otherwise it won’t make sence when viewing the table.

`count(*)` is the only aggregate function that does **not** ignore `null`, because some other fields might be filled. But, if there are only `null` values in the entire table, then even `count(*)` will return 0.

Note:
- `COUNT(*)` is the most-optimized and fastest way to count rows

### Predicate Order

1. where (before grouping)
2. having (after grouping)

## Subqueries

| Clause       | Meaning                     |
| ------------ | --------------------------- |
| `in`         | exact match                 |
| `some`       | like `or` gate              |
| `all`        | like `and` gate             |
| `exists`     | less strict version of `in` |
| `not exists` | 0                           |
| `unique`     | at most once (0/1)          |

### `in`

```mysql
select count(distinct cid) from instructor
	where semester = "Fall" and year = 2009 and
		cid in (
      		select cid from instructor where semester = "Spring" and year = 2010
    	);

## equivalent to
select distinct i1.cid
	from instructor i1, instructor i2
	where i1.semester = "Fall" and
	i1.year = 2009 and
	i2.semester = "Spring" and
	i2.year = 2010 and
	i1.cid = i2.cid;
```

### `some`

```mysql
select distinct name from instructor
	where age > some (select age from instructor);
	
	where age not > some (select age from instructor);
```

### `all`

not all = not in

```mysql
select distinct name from instructor
	where age > all (select age from instructor);
	
	where age not > all (select age from instructor);
```

### `exists`

```mysql
select cid
from section as s
where semester = "Fall" and year = 2009
	and exists
	(
    	select * from section as T
    	where semester = "Spring" and year = 2010
    	and s.cid = t.cide
	);
```

### `not exists`

```mysql
select distinct s.id, s.name
from student as s
where not exists
(
	(select course_id from course where dept_name = "Biology")
	except
	(select t.course)
)
```

### `unique`

```mysql
select t.cid from course as t
where unique(select r.cid from section as r where t.cid = r.)
```

## `from` subqueries

```mysql
select deptName, avgSalary
from (
  select deptName, avg(salary) as avgSalary
  from Instructors
  group by deptName
)
where age > 30;

## equivalent to

select deptName, avg(salary) as avgSalary
from Instructors
group by deptName
having age > 30;
```

## `with` clause

```mysql
with maxTable(year, budget) as (
	select max(year), max(budget) from department
) ## no semi-colon
select department.name
from department, maxTable
where department.budget = maxTable.budget;
```

## Scalar Subquery

```mysql
select deptName, (
	select count(*)
  from instructor
  where department.deptName = instructor.deptName
) as numInstructors
from department;

## equivalent

select department.deptName, count(*) as numInstructors
	from instructor, department
	where department.deptName = instructor.deptName
	group by deptName;
```

## Views

Temporary table

```mysql
create view view_name as
select *
from students;
```

## CTE `with` Clause

Temporary [view](#views), which you only need once.

Useful when you need just an extra column, and for [Recursive CTE](#Recursive-CTE).

```mysql
with table_with_dpr as
(
	select
  	*,
  	dob - today() as AGE
)
select *
where AGE > 30;
```

## Recursive CTE

```mysql
with recursive cte (id, name, parent_id) as
(
  select     id,
             name,
             parent_id
  from       products
  where      parent_id = 19
  union all
  select     p.id,
             p.name,
             p.parent_id
  from       products p
  inner join cte
          on p.parent_id = cte.id
)
select * from cte;
```
