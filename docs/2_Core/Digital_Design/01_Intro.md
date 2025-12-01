## Binary constants

0 = off/high-level voltage

1 = on/low-level voltage

## Logic system

There is 10% tolerance

### Positive

- 0 =  0v ()
- 1 = 5v (4.5v - 5v)

### Negative

- 0 = 0v
- 1 = 

## Pulse Notations

### Rise time

Time from 10% to 90% V

### Fall Time

Time from 90% to 10% V

### Pulse Width

Time from 50% V of one end to 50% V of the other end

### Duty Cycle

T = time period = T~on~ + T~off~

## Verilog

Verifying logic

Hardware Description language

Execution of lines in program happens concurrently

### Ports

Input, outputs, wires, registers

## Multiple Bits

Create an array

In verilog, arrays are numbered in reverse

``` verilog
input[3:0] a; // 4bit          3 2 1 0
input[7:0] b; // 8bit  7 6 5 4 3 2 1 0

a = 4'b0000;
b = 8'b1010100;
```

## Modelling

### Types

| Gate level                                            | Dataflow                                     | Behavior Modelling                                      | Structural                               |
| ----------------------------------------------------- | -------------------------------------------- | ------------------------------------------------------- | ---------------------------------------- |
| low level                                             | medium level                                 | high level                                              |                                          |
| definining gates                                      | mathematical (arithmetic/boolean) operations | describe the behavior of the circuit                    |                                          |
| custom gates or inbuilt gate like and(), or(), nand() | `assign`                                     | 2 structures procedures - `initial`, `always`           | 2types of module instantiation           |
| `or(y, a, b);`<br />`or(z, c, d);`                    | `assign y = a|b;`<br />`assign z = c|d;`     | `if(a==0 &b==0)`<br />`y = 0` <br />`else`<br />`y = 1` | `or g1(y, a, b);`<br />`or g2(z, c, d);` |

|           | `initial`                                                  | `always`                 |
| --------- | ---------------------------------------------------------- | ------------------------ |
|           | runs statement only once, during the entire simulation run | continuous infinite loop |
| Starts at | t = 0                                                      | t = 0                    |
|           |                                                            |                          |

``` verilog
initial
  begin
    statement;
  end
  
always
  begin
    statement;
  end
  
//behavioral
//example
always
  #5 A = ~A; // invert every 5 nanoseconds

always
  begin
    #2 a = 1;
    #3 a = 0;
  end
```

### Examples

1. y = a or b or c

```verilog
module or_gate(A, B,C, y); // ports, initialisation
  input A, B, C; // declaration
  output y; // declaration
  
  // description, always (output, input)
  or(y, A, B, C); // gate level modelling
  assign y = A|B|C; // data flow modelling
  end module
```

2. p = s', q = I~0~S, r = I~1~S
   y = q+r

``` verilog
module circuit_1(I0, I1, S, y);
	input I0, I1, s;
  output y;
  wire p, q, r;
  
  not #1 G1(p, s); // #1 = time delay of 1ns
  and #2 G2(q, i0, p); // #2 = time delay of 2ns
  and #2 g3(r, s, i1);
  or #2 g4(y, q, r);
  end module
```

3. p = s', q = I~0~S, r = I~1~S
   y = q+r
   Dataflow

   ``` verilog
   module circuit_1(I0, I1, S, y);
   	input I0, I1, s;
     output y;
     wire p, q, r;
     
     assign #1 p = Ns;
     assign #2 q = i0 & p;
     assign #2 r = i1 & s;
     assign #2 y = q | r;
   
     end module
   ```
   
4. Behavioral
    ``` verilog
    module or_behavior(a,b,z);
      output reg z;
      always @ * // (a or b) // sensitivity list
        begin
          if (a == 1'b0 & b == 1'b0) // 1 bit binary
    				z = 1'b0;
          else
            z = 1'b1;
        end
    endmodule
    
    always @ * begin   
      case(s)
        2'b00: // statement
        2'b01: // statement
        2'b10: // statement
        2'b11: // statement
      endcase
    end
    ```
    

## Breadboard

![Breadboard](./assets/Breadboard.svg){ loading=lazy }