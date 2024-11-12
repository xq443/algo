# Automation Skills

## CI/CD

### Question 1: What is CI/CD and why is it important?

- **Continuous Integration (CI)**: Developers frequently merge code changes to a central repository. Each commit triggers automated builds and tests to catch integration issues early. CI focuses on ensuring code quality by automating the testing process and reducing manual testing efforts.
- **Continuous Delivery (CD)**: Following CI, Continuous Delivery automates the release process so that code is always in a deployable state. After CI tests pass, the code can be manually deployed to production or staging environments. CD emphasizes getting code to a "production-ready" state so that release cycles are quicker and more predictable.

## Kubernetes

[Documentation Link](https://blog.csdn.net/su2231595742/article/details/124182312?ops_request_misc=%257B%2522request%255Fid%2522%253A%25220F8689D8-9119-4A78-9771-B4915EFFAD62%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=0F8689D8-9119-4A78-9771-B4915EFFAD62&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-124182312-null-null.142^v100^pc_search_result_base8&utm_term=kubernetes&spm=1018.2226.3001.4187)

### Containers and Orchestration:
- Containers allow applications to be packaged with their dependencies, ensuring consistent environments across platforms. Kubernetes manages (orchestrates) these containers at scale, handling tasks like deployment, load balancing, and self-healing.

### Core Components:
- **Pods**: The smallest deployable units in Kubernetes, which can contain one or more tightly coupled containers. Containers within a pod share resources and networking. Usually, 1 application per pod. Each pod gets its own IP address.
- **Service**: A static or permanent IP address that can be attached to each pod, since the lifecycle of the service and pod are not connected. Even if the pod dies, the service and its IP address will stay.
- **Ingress**: Instead of the service, the request goes first into ingress, which does the forwarding to the service.
- **Configuration Map**: URLs of a database (non-confidential).
- **Secret**: Base64 encoded format.
- **Volumes**: It attaches physical storage to the pod, which can either be on the same server as the pod or on remote storage outside the Kubernetes cluster.

### Process of Pod Creation:
1. **User Submission**: The user submits the pod creation request to the API server using `kubectl` or another API client.
2. **API Server Processing**: The API server generates the pod object information and stores it in `etcd`, then returns a confirmation to the client.
3. **State Reflection**: The API server reflects the changes in the pod object stored in `etcd`. Other components use the watch mechanism to track changes.
4. **Scheduler Assignment**: The scheduler detects the new pod object and assigns a host to the pod, updating the information in the API server.
5. **Kubelet Action**: The kubelet on the assigned node detects the scheduled pod, attempts to start the container using Docker, and sends the result back to the API server.
6. **State Update**: The API server receives the pod status information and stores it in `etcd`.

### Process of Pod Termination:
1. **User Deletion Request**: The user sends a command to the API server to delete the pod object.
2. **API Server Updates**: The pod object information in the API server is updated over time. During the grace period (default 30 seconds), the pod is considered dead.
3. **Marking as Terminating**: The pod is marked as being in the terminating state.
4. **Kubelet Initiates Shutdown**: The kubelet, upon detecting the pod’s terminating state, starts the pod shutdown process.
5. **Endpoint Controller Updates**: The endpoint controller removes the pod from the endpoint lists of any services that match this pod.
6. **PreStop Hook Execution**: If the pod has a `preStop` hook defined, it is executed synchronously after the pod is marked as terminating.
7. **Container Stop Signal**: The container processes within the pod receive a stop signal.
8. **Grace Period Ends**: After the grace period, if there are still running processes in the pod, the pod receives an immediate termination signal.
9. **Final Deletion**: The kubelet requests the API server to set the pod’s grace period to 0 to complete the deletion. At this point, the pod is no longer visible to the user.

### Nodes:
- Physical or virtual machines where Kubernetes workloads run. Each node contains a Kubelet (agent) and a container runtime (like Docker). Each node has one kubelet processing running on it, and containers of different applications deployed.

### Cluster:
- A group of nodes managed by Kubernetes, forming a platform for deploying applications.

### Control Plane:
- The brain of the cluster, handling scheduling, API server functions, and health monitoring. It consists of the **API Server**, **Scheduler**, **Controller Manager**, and **etcd** (a key-value store for cluster data).
- **API server**: The entry point to the Kubernetes cluster, offering a web interface or client command.
- **Controller Manager**: Keeps an overview of the cluster's health, managing tasks like container restarts.
- **Scheduler**: Decides which node a new pod should be scheduled on based on workload and available resources.
- **Etcd**: A key-value store holding the current status of the Kubernetes cluster and provides snapshots for recovery.

### Services:
- Enable stable networking within the cluster, providing a consistent endpoint to access a set of pods, even if they move around nodes. It acts like a load balancer, forwarding requests to the least busy pod.

### Deployments:
- Blueprints for the pods, indicating the number of replicas, and allowing scaling up or down.

### StatefulSet:
- Ensures that databases are synchronized, making sure writes and reads are consistent without database inconsistencies.

### Horizontal Pod Autoscaler (HPA):
- A controller that scales pod-based resources up or down depending on application workload. It scales by increasing/decreasing the number of replicas once pre-configured thresholds are met. Excludes objects that cannot be scaled, such as DaemonSets.

## Terraform

## Docker

## Apache Airflow

Apache Airflow is a popular platform used to programmatically author, schedule, and monitor workflows.

### Question 1: What is Apache Airflow and what are its main components?
Answer: Apache Airflow is an open-source platform for programmatically authoring, scheduling, and monitoring workflows. It allows you to define workflows as directed acyclic graphs (DAGs) of tasks. The main components of Airflow are:
- **DAG**: A collection of tasks organized to reflect their dependencies and relationships.
- **Task**: A single unit of work within a DAG.
- **Scheduler**: Responsible for scheduling tasks and ensuring their execution order.
- **Executor**: Determines how tasks are executed (e.g., locally, with Celery, Kubernetes).
- **Web Server**: Provides a user interface to monitor and manage workflows.
- **Metadata Database**: Stores the state and history of tasks and DAGs.

### Question 2: How do you define a DAG in Airflow?
Answer: A DAG in Airflow is defined using Python code. Here is a simple example:

```python
from airflow import DAG
from airflow.operators.dummy_operator import DummyOperator
from airflow.utils.dates import days_ago

# Define the DAG
dag = DAG(
    'example_dag',
    description='An example DAG',
    schedule_interval='@daily',
    start_date=days_ago(1),
    catchup=False
)

# Define tasks
start = DummyOperator(task_id='start', dag=dag)
end = DummyOperator(task_id='end', dag=dag)

# Set task dependencies
start >> end
```

### Question 3: What are some common operators in Airflow?
Answer: Airflow provides a variety of operators to perform different tasks:
- **DummyOperator**: Used for placeholder tasks.
- **BashOperator**: Executes a bash command.
- **PythonOperator**: Executes a Python function.
- **EmailOperator**: Sends an email.
- **SqlOperator**: Executes an SQL command.
- **BranchPythonOperator**: Allows branching based on a Python function result.

### Question 4: How does Airflow handle task dependencies?
Answer: Airflow handles task dependencies using the `>>` and `<<` operators to set upstream and downstream relationships. You can also use the `set_upstream` and `set_downstream` methods.

```python
task1 = DummyOperator(task_id='task1', dag=dag)
task2 = DummyOperator(task_id='task2', dag=dag)
task3 = DummyOperator(task_id='task3', dag=dag)

# Set dependencies
task1 >> task2
task2 >> task3
```


markdown
Copy code
### Question 5: What is the role of the Airflow Scheduler?
Answer: The Airflow Scheduler is responsible for scheduling tasks and ensuring they are executed in the correct order based on their dependencies and the DAG’s schedule.

### Question 6: How can you monitor and troubleshoot workflows in Airflow?
Answer: You can monitor and troubleshoot workflows in Airflow using the web interface, which provides several features:
- Viewing DAG and task status.
- Accessing task logs for debugging.
- Triggering manual runs of tasks or DAGs.
- Examining DAG execution history.

### Question 7: How does Airflow handle retries?
Answer: Airflow allows task retries by setting the `retries` parameter in the task definition. You can also define the `retry_delay` to specify the time interval between retries.

```python
task = PythonOperator(
    task_id='example_task',
    python_callable=my_function,
    retries=3,
    retry_delay=timedelta(minutes=5),
    dag=dag
)
```

### Question 8: What is the use of XComs in Airflow?
Answer: XComs (short for "Cross-communication") allow tasks to share information. A task can push a value to XCom, and another task can pull that value to use it.

```python
# Push a value
task_instance.xcom_push(key='my_key', value='my_value')

# Pull the value
value = task_instance.xcom_pull(task_ids='task_id', key='my_key')
```


### Question 9: How does Airflow scale?
Answer: Airflow scales by using multiple workers to execute tasks concurrently. The `Executor` defines how tasks are distributed across workers, with options for local execution, Celery, and Kubernetes-based execution.




