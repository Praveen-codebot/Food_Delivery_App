# 🍴 Food Delivery App

A **Swiggy/Zomato-style food delivery web application** built using **Java (JSP, Servlets, MVC)** and **MySQL**.  
Users can sign up, log in, browse restaurants, view menus, and place food orders — all from a simple web interface.

---

## 🚀 Features

✅ User Authentication (Signup, Login, Logout)  
✅ Password Encryption for Secure Login  
✅ Restaurant Listings and Menu Display  
✅ Add to Cart and Order Placement  
✅ Session Management with JSP and Servlets  
✅ Responsive UI using HTML, CSS  
✅ MVC Architecture (DAO, Service, Controller Layers)  
✅ MySQL Database Integration via JDBC  
✅ Maven Project Structure for Easy Dependency Management  

---

## 🏗️ Project Structure

```
Food_Delivery_App/
│
├── pom.xml                  # Maven dependencies
├── src/                     # Java source files
│   ├── com.bob.dao/         # DAO interfaces and implementations
│   ├── com.bob.model/       # Java model classes
│   ├── com.bob.service/     # Business logic layer
│   ├── com.bob.servlet/     # Servlets (Controllers)
│   └── com.bob.util/        # Utility classes (DB connection, etc.)
│
├── WebContent/ or webapp/   # JSP pages, CSS, images, etc.
│   ├── jspPages/
│   │   ├── login.jsp
│   │   ├── signup.jsp
│   │   ├── home.jsp
│   │   ├── restaurants.jsp
│   │   └── cart.jsp
│   ├── css/
│   └── images/
│
└── README.md                # Project documentation
```

---

## 🛠️ Technologies Used

| Layer | Technology |
|-------|-------------|
| Frontend | HTML, CSS, JSP |
| Backend | Java (Servlets, JDBC, MVC) |
| Database | MySQL |
| Build Tool | Maven |
| Server | Apache Tomcat |
| IDE | Eclipse / IntelliJ IDEA |

---

## ⚙️ Setup Instructions

1. **Clone or Download** this repository  
   ```bash
   git clone https://github.com/yourusername/Food_Delivery_App.git
   ```

2. **Open in Eclipse/IntelliJ**  
   - Import as a **Maven Project**

3. **Configure Database**  
   - Create a database named `food_delivery`
   - Import your SQL schema or run:
     ```sql
     CREATE DATABASE food_delivery;
     ```
   - Update your `DbConnection.java` file with your MySQL credentials.

4. **Run on Server**  
   - Right-click project → Run As → Run on Server → Select **Apache Tomcat**  
   - Open in browser: `http://localhost:8080/Food_Delivery_App/`

---

## 🧠 Future Enhancements

- Add Admin Panel for managing restaurants and orders  
- Integrate payment gateway (Razorpay/Stripe)  
- Add real-time order tracking  
- Implement REST APIs for mobile app integration  
- Add ratings and reviews system  

---

## 📸 Screenshots

![Home Page](images/screenshots/home.png)
![Login Page](images/screenshots/login.png)
![Signup Page](images/screenshots/signup.png)
![Cart Page](images/screenshots/cart.png)

---

## 🧑‍💻 Author

**Praveen Kumar Potti**  
📧 praveen15537@gmail.com
💼 [LinkedIn Profile](https://www.linkedin.com/in/praveen-potti-0b229325a/)  
🌐 [GitHub Profile](https://github.com/Praveen-codebot)

---

## 📜 License

This project is licensed under the **MIT License** — feel free to use and modify it.

---
