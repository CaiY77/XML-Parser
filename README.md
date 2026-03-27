# Business XML Parser

## Documentation

### **Customize what information you want to retrieve from parser. Select what you want to extract.** 

---

### **getFile(myWriter);**
>*Change/Edit the file path below to reference different XML files or target different filepaths*
```Java
document = builder.parse(new File("ENTER FILEPATH/FILENAME"));
```
---

### **getBuRules(myWriter);**
>*BR Name and ID grabs*

---

### **getWorkerRules(myWriter);**
>*Worker Rule Name and ID grabs*

---

### **getBuRulesPlus(myWriter);**
>*BR Name, Id, BU, AG*

---

### **getChatSpecName(myWriter);**
>*Chat Spec Name and # of Occurrence* <br> *Occurrence values are not accurate for clients that use "business-action" tag* <br> *Seeing 2 of the same entries with different occurences indicates proactive/c2c usages. First value is c2c, second is proactive*

---

### **getC2CSpecName(myWriter);**
>*C2C Spec Name and # of Occurrence* <br> *Occurrence values are not accurate for clients that use "business-action" tag*

---

### **getCustomEvents(myWriter);**
> *BR Name, Id, BU, AG*

---

### **getVars("number");**
>*Get a list of all variables. <br>Adding a second argument will specify a single variable type.<br> Possible inputs: number, string, string-list, object, map, boolean*
```Java
// default, ALL variables
getVars(myWriter);
// Currently Unavaliable
getVars(myWriter,"number");
```

---

### **getCG(myWriter);**
>*All Content Groups*

---

### **getJS(myWriter);**
>*All Js-Functions*

---

### **getPreRichMedia(myWriter);**
>*All CI V2 Prechat Surveys*

---
