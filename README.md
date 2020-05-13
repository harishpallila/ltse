# ltse
ltse assignment

Input:
  Please provide 3 program args to main class: MyExchange
  java MyExchange "/Users/harishpallila/Downloads/trades.csv" "/Users/harishpallila/Downloads/symbols.txt" "/Users/harishpallila/Downloads"

Output:
  4 out files will be generated
    a. accepted.txt: accepted trades
    b. rejected.txt: accepted trades
    c. accepted_orders_json.txt: accepted trades in json format for downstream
    d. rejected_orders_json.txt: rejected trades in json format for downstream

External jars required:
	<classpathentry kind="lib" path="/Users/harishpallila/eclipse-workspace/jars/jackson-annotations-2.9.8.jar"/>
	<classpathentry kind="lib" path="/Users/harishpallila/eclipse-workspace/jars/jackson-core-2.9.8.jar"/>
	<classpathentry kind="lib" path="/Users/harishpallila/eclipse-workspace/jars/jackson-databind-2.9.8.jar"/>
