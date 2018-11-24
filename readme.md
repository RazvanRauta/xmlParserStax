#Xml parser using StAX

It's parsing a file with product orders and creating new files with the products sorted by the supplier.
Input File: 
```xml
<myxml>
<?xml version="1.0" encoding="UTF-8"?>
<orders>
    <order created='2012-07-12T15:29:33.000' ID='2343'>
        <product>
            <description>Sony 54.6" (Diag) Xbr Hx929 Internet Tv</description>
            <gtin>00027242816657</gtin>
            <price currency="USD">2999.99</price>
            <supplier>Sony</supplier>
        </product>
        <product>
            <description>Apple iPad 2 with Wi-Fi 16GB - iOS 5 - Black</description>
            <gtin>00885909464517</gtin>
            <price currency="USD">399.0</price>
            <supplier>Apple</supplier>
        </product>
        <product>
            <description>Sony NWZ-E464 8GB E Series Walkman Video MP3 Player Blue</description>
            <gtin>00027242831438</gtin>
            <price currency="USD">91.99</price>
            <supplier>Sony</supplier>
        </product>
    </order>
    <order created='2012-07-13T16:02:22.000' ID='2344'>
        <product>
            <description>Apple MacBook Air A 11.6" Mac OS X v10.7 Lion MacBook</description>
            <gtin>00885909464043</gtin>
            <price currency="USD">1149.0</price>
            <supplier>Apple</supplier>
        </product>
        <product>
            <description>Panasonic TC-L47E50 47" Smart TV Viera E50 Series LED HDTV</description>
            <gtin>00885170076471</gtin>
            <price currency="USD">999.99</price>
            <supplier>Panasonic</supplier>
        </product>
    </order>
</orders>
</myxml>
```
Output files:
```xml
<myxml>
<?xml version="1.0" encoding="UTF-8"?>
<products>
  <product>
    <description>Apple iPad 2 with Wi-Fi 16GB - iOS 5 - Black</description>
    <gtin>885909464517</gtin>
    <price currency="USD">399.0</price>
    <orderId>2343</orderId>
  </product>
  <product>
    <description>Apple MacBook Air A 11.6" Mac OS X v10.7 Lion MacBook</description>
    <gtin>885909464043</gtin>
    <price currency="USD">1149.0</price>
    <orderId>2344</orderId>
  </product></products>
 
    
<?xml version="1.0" encoding="UTF-8"?>
<products>
  <product>
    <description>Sony 54.6" (Diag) Xbr Hx929 Internet Tv</description>
    <gtin>27242816657</gtin>
    <price currency="USD">2999.99</price>
    <orderId>2343</orderId>
  </product>
  <product>
    <description>Sony NWZ-E464 8GB E Series Walkman Video MP3 Player Blue</description>
    <gtin>27242831438</gtin>
    <price currency="USD">91.99</price>
    <orderId>2343</orderId>
  </product></products>
        
<?xml version="1.0" encoding="UTF-8"?>
<products>
  <product>
    <description>Panasonic TC-L47E50 47" Smart TV Viera E50 Series LED HDTV</description>
    <gtin>885170076471</gtin>
    <price currency="USD">999.99</price>
    <orderId>2344</orderId>
  </product></products>
 </myxml>
 ```
 
 
 @By Razvan Rauta

