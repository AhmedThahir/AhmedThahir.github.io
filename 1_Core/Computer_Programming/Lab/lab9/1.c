#include <stdio.h>

int main()
{
	int n = 5;
	float a[n];

	
	for(int i = 0; i<n; i++)
	{
		printf("\n Marks in subject %d:", i+1);
		scanf(" %f", &a[i]);
	}

	float sum = 0;
	for(int i = 0; i<n; i++)
		sum += a[i];

	float avg = sum/n;
	char grade;

	if (avg >= 90)
		grade = 'A';
	else if (avg >= 80)
		grade = 'B';
	else if (avg >= 70)
		grade = 'C';
	else if (avg >= 60)
		grade = 'D';
	else if (avg >= 40)
		grade = 'E';
	else
		grade = 'F';
	
	printf("\n\nThe percentage of the student: %f\nThe grade of the student: %c", avg, grade);


	return 0;
}
