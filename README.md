# Network  

**Connect to the network**

In order to perform network operations in your application, your manifest must include the following permissions:
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```  
Most network-connected Android apps use HTTP to send and receive data. The Android platform includes the HttpsURLConnection client, which supports TLS, streaming uploads and downloads, configurable timeouts, IPv6, and connection pooling.   

We have used `Retrofit` library to do network operations. 

**What is Retrofit?**  
**[Retrofit](https://square.github.io/retrofit/)** is a REST Client library (Helper Library) used in Android and Java to create an HTTP request and also to process the HTTP response from a REST API. It was created by Square, you can also use retrofit to receive data structures other than JSON, for example SimpleXML and Jackson.  

- API that we have used in this project is: **[ip-api](https://ip-api.com/)**  
