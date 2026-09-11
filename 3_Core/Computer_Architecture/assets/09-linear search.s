.data
array: .word 1, 24, 56, 78, 90, 100, 323, 4326, 57456, 74554
length: .word 10
msgNotFound: .asciiz "Item is not in the array"
msgFound: .ascii "Index at which item is found in the array:"

.text
main:

  la $a0, array               ## Load array into $a0
  li $a1, 74554                   ## Load item to search 
  lw $a2, length              ## Load length of array into $a2
  li $t0, 0                   ## Load not found flag as 0

loop:
  bge $t0, $a2, NotFound      ## If $t0 > $a2, we are outside the array
  lw $t1, 0($a0)              ## Load the element into t1
  beq $t1, $a1, Found         ## Found the element
  addi $a0, $a0, 4            ## Add 4 (1 word index) to the array
  addi $t0, $t0, 1            ## Add one to the index
  j loop 

Found:
  la $a0, msgFound            ## Load not found text into $a0
  li $v0, 4                   ## Load print string syscall
  syscall                     ## Print the string

  add $a0, $t0, $0            ## Move $v1 into $a0
  li $v0, 1                   ## Load print integer sys
  syscall                     ## Print the integer

  j exit                      ## Exit the program

NotFound:
  la $a0, msgNotFound         ## Load not found text into $a0
  li $v0, 4                   ## Load print string syscall
  syscall                     ## Print the string

exit:
  li $v0, 10
  syscall
.end main