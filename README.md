# SchedulingSystem

**Description:**

This is a sample of play framework project on which we have CRUD operations for two entities (Student, Class).

**Overview:**

This project uses Java Play Framework, Ebean ORM.

**Requisites:**

Running mysql and a created table named 'scheduling'.

**Steps to execute it:**

- clone the project.
- execute the following commands:
    sbt clean compile
    sbt run

**Resources:**

You can find the available resources on the routes file.

**Queries:**

On the routes GET from Students or Class you can execute some queries like:

http://localhost:9000/schedulingSystem/student?query=WHERE last_name='Luis'
http://localhost:9000/schedulingSystem/class?query=WHERE title='Libro'

Note: queries are optional, leave it on blank if you want to get all records.

**TODOS:**

- Add a API documentation using swagger for play.
- Add an errorHandler in order to throw accurate http response codes.
- Add unit test to verify that everything work as expected.
- Add constraint validation, this in order to provided end users friendly messages if wrong data provide.



