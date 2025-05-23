# Crypto MS

📋 **Description**  
Crypto MS is a Java Spring Boot microservice that provides cryptocurrency data using the CoinMarketCap API.

🛠️ **Prerequisites**
- Java 21 installed
- CoinMarketCap API key

🔑 **Setting Up the API Key**
1. Create a file named `secrets.properties` in the root of the project.
2. Add the following line to the file:
   ```
   coinmarketcap.api.key=<YOURCOINMARKETCAPAPIKEY>
   ```  
   Replace `<YOURCOINMARKETCAPAPIKEY>` with your actual CoinMarketCap API key.

▶️ **Running the App**
1. Ensure Java 21 is installed.
2. Set up the `secrets.properties` file as described above.
3. Open the project in your preferred IDE.
4. Run the application.
5. Verify that it is running on port 9091.

**Note:** This microservice is designed to be used in conjunction with the GraphDailyBriefing application.