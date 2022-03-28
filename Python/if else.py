#If else statement 
x = 2
y = 4
if x > y:
    print("x is greater than y")
else:
    print("y is greater than x")

#Showing type
x = 4
y = "Samuel"
print(type(x))
print(type(y))

#Unpacking
fruits = ["Banana", "Apple", "Orange"]
x, y, z = fruits
print(x)
print(y)
print(z)

#
a = "This "
b = "is "
c = "working "
print(a + b + c + "well")

d = a + b + c
print(d + "well")

#Type casting
n = str(5)
m = "String"
print(n + m)

#Multiple variables
x = y = z = "Orange"
print(x)
print(y)
print(z)

import random
print(random.randrange(1,100))
