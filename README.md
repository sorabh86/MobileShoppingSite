# UIGO Mobile Shopping Site

## Introduction

   Demands of mobile phones are high, there are many shops selling mobile phone locally. We are developing a mobile phone shopping system that enables online selling and purchase of products (in this case mobile phones). Company or individual have numerous models of smartphone to sell to the customer.
    Here every shop selling mobile phones individually maintains various registers. Those register contains details of customer’s details, inventory items, sales, payments, etc. and it is really a time consuming process. We are manually checking inventory for available handset and also manually attending each and every customers, describing details about item, and offering price. This interactive process required large man force to activities.
    Online Shopping is getting popular now days. We are developing a Web application that depict online shopping for mobile phones and purchasing using Payment Gateway. Using this software, company can improve the efficiency of their services and manage records at one place.
   
## Objectives
   The main objective of our system is to provide user friendly e-commerce platform for selling and purchasing mobile phones online. The system helps entrepreneur to manage and track records of sales as well as this software helps customer to find different mobiles, their features, and new updates easily.
    The Central objective is for increase selling as well making business much simple to manage, putting all records available on one place for authorized agents or partners of the business.  System will help us to manage records of customers details, inventories records, sales records. These records could help us in near future to predict better investment plans in future, for examples we have a list of happy customers we can offer them better deals, we have previous year records of sales and remaining inventories those can help us in planning of supply and demands.
    It is designed such a way that our customer’s or someone interested in our mobile phone can view all the updates of the mobile from any place through online. The software will help in easy maintaining and updating products in the website for the management. Overall system will benefits  us more in near future on both perspective, whether managing business or our customers for quickly purchase mobile phones from our website.
    
### CSRF ATTACK Protection on forms
```html
<!-- _csrf token only injected if you use th:action for using plane action attribute inject it manually -->
	<input th:if="${_csrf!=null}" type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
```

# fix file with space doesn't showing
application.properties
spring.mvc.pathmatch.matching-strategy=ant-path-matcher

#### ADD days on Date
```
/* javascript */
/****** @arg String date, @arg Integer number *******/
function addDays(date, number) {
    const newDate = new Date(date);
    return new Date(newDate.setDate(newDate.getDate() + number));
}

/** java **/
Date date = new Date();
System.out.println("before date: "+date);

Instant now = date.toInstant();
System.out.println("now: "+now);
Instant plus = now.plus(Duration.ofDays(7));
System.out.println("plus: "+plus);

Date newDate = Date.from(plus);
System.out.println("after date: "+newDate);

```
