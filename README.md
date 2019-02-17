###Purpose
This program is used to calculate the profit and tax payable allocation of each member by given fund information and member information.

The fund information includes income, expense, and tax rate (between 10% and 15% inclusive)

The member information includes the member's name, proportion, and whether it's pension or not

By given input of fund and member information, the program will output the total profit and total tax payable, as well as the profit and tax for each member
###Assumption

The input income and expense are valid non-negative integer, and income is not less than expense

The tax rate is between 10% and 15%
Each member's proportion is between 0 and 100%
If a member is pension, this member do not need to pay for tax
It is not allowed that all members are pension
All results are integer
###Instruction
There is an input template file under the directory "src/main/resources/SMSFFile.txt", which is like this:
````
100, 10, 10%
Jack, 60%, false
Rose, 30%, false
Ruth, 10%, true
````
The first line is fund detail, including income, expense, and tax rate in order. The tax rate is in format of "x%"

The following lines are member information. One line for one member. Member's name, proportion, and whether is pension in order

Using coma to separate each element. 

Changing the values for fund information and member details in the template as input

To run the program, please download the project to any directory. Then open terminal, change to the project directory. The name of the project is "smsf". So in command line, you can type like this:
````
cd d/java/smsf
````

And type the following to run the program
````
./run-smsf.sh
````
Or you can just use your own IDE to open this project, and run the main method in SmsfApplication class

Then the output will be printed into console
````
Profit: 90
Tax payable: 9

Ruth's profit : 9
Rose's profit : 27
Jack's profit : 54

Ruth's tax allocation: 0
Rose's tax allocation: 3
Jack's tax allocation: 6
````


