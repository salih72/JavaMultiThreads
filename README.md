### Multi-Threaded Data Ingestion with Spring Boot and Multiple Databases


This project is a Spring Boot application designed to read large files and save their contents into different types of databases (PostgreSQL, MySQL, MariaDB) concurrently using multi-threading. The application demonstrates how to configure multiple data sources in Spring Boot and perform asynchronous data saving using custom interfaces and factory patterns.

-----
Features
-----
Multi-Threaded Processing: Reads large files and processes them using multiple threads for faster execution.

Multiple Database Support: Supports PostgreSQL, MySQL, and MariaDB as data storage options.

Dynamic Database Selection: Allows runtime selection of the target database for data saving.

Asynchronous Execution: Utilizes asynchronous tasks for non-blocking operations.

Custom Factory Pattern: Implements a factory to create appropriate database saver instances based on the selected database.

RESTful API: Exposes endpoints to trigger data saving operations.

Spring Data JPA: Uses JPA repositories for database interactions.

Docker Compose Integration: Provides a docker-compose.yml file to set up the required databases easily.

-----

Architecture
-----
The application is structured into several key components:

-----
**1. Configurations (config package):**

AsyncConfig: Configures the thread pool executor for asynchronous tasks.

JpaConfig: Sets up JPA vendor adapters.

DataSourceConfigs: Configures data sources, entity managers, and transaction managers for PostgreSQL, MySQL, and MariaDB.

-----
**2. Controllers:**

DataController: Handles incoming HTTP requests to initiate data saving operations.

-----
**3. Services:**

DataService: Manages reading data from files and distributing work among threads.

DatabaseSaver Implementations:

PostgresSaver

MysqlSaver

MariadbSaver

-----
**4. Repositories:**

JPA Repositories for each database type (PostgreSQL, MySQL, MariaDB).

-----
**5. Entities:**

Data Entities corresponding to each database type.

-----
**6. Factories:**

DatabaseSaverFactory: Creates instances of DatabaseSaver based on the database type.

-----
**7. Interfaces:**

DatabaseSaver: Defines the contract for saving data asynchronously.

-----
**8. Docker Compose:**

docker-compose.yml: Sets up PostgreSQL, MySQL, and MariaDB instances.


-----
<img width="632" alt="Screenshot 2024-10-02 at 10 41 51 AM" src="https://github.com/user-attachments/assets/d9216c1a-a74d-45c9-a71a-eb17e3c45bea">

-----
<img width="584" alt="Screenshot 2024-10-02 at 10 42 19 AM" src="https://github.com/user-attachments/assets/7cd858c9-b5c9-45fe-8968-ad67e58da1e1">

-----
<img width="416" alt="Screenshot 2024-10-02 at 10 47 42 AM" src="https://github.com/user-attachments/assets/5f3ef3a5-22e7-43aa-a887-85e48a2a4b2d">

-----
<img width="262" alt="Screenshot 2024-10-02 at 10 43 47 AM" src="https://github.com/user-attachments/assets/f509b9ff-92cf-44cc-bff2-28aca0c376da">


