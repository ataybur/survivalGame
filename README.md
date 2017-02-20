# survivalGame
@author Burak Atay
@since 20.02.2017

Proje Java 8 ile yazýlmýþtýr.
Proje runnable jar'a çevirilir.
java -jar <jarname>.jar <input-file-name> <output-file-name> komutu kullanýlarak uygulama çalýþtýrýlýr.

Proje içinde yedi adet package bulunur. 

com.ataybur.constants: final public static deðerlerin bulunduðu pakettir.

com.ataybur.enums: Enum sýnýflarýnýn bulunduðu pakettir.
-LineTypes: Satýr tiplerini ve bu tiplerin hangi regex'e eþlendiðini tutan enum sýnýfýdýr.

com.ataybur.main: Uygulamanýn yürütüleceði Main sýnýfýnýn bulunduðu pakettir.

com.ataybur.lambda: functional interface'lerin bulunduðu pakettir.
-ConsumerThrowing: Build-in functional interface'ler, içlerinde exception throwing'i desteklemediði için böyle bir interface yazýlmýþtýr.

com.ataybur.pojo.base: Uygulamada kullanýlacak pojolardan bazýlarýnýn kalýtacaðý sýnýflarý içeren pakettir.
-Character: Hero ve Enemy sýnýflarýnýn ortak özellikleri olan attackPoint ve hp deðerlerinin olduðu abstract sýnýftýr.

com.ataybur.pojo: Uygulamada kullanýlacak pojolarýn bulunduðu pakettir.
-Hero: Hero için gerekli deðerleri tutan sýnýftýr. Character sýnýfýna eklenen bir özelliði yoktur. 
-Enemy: Düþmanlar için kullanýlan bir sýnýftýr. Character sýnýfýna Species özelliði eklenmiþtir.
-Field: Oyun alanýný belirten bir sýnýftýr. Range ve enemy list bilgilerini tutar.
-Context: Oyunun baðlamýný belirtir. Oyunda yer alan Hero, Enemy türleri, Field gibi bilgileri tutar. Bütün oyun boyunca bir kez yaratýlmasý gerektiði 
 için singleton prensibiyle yazýlmýþtýr. 

com.ataybur.utils: Uygulamadaki çeþitli iþleri gerçekleþtiren methodlarýn olduðu util sýnýflarýný içeren pakettir. Servis paketi gibi düþünülebilir.
-ContextUtils: Context pojosuna dair iþlerin yapýldýðý utils sýnýfýdýr.
-ParserUtils: Girdi olarak verilen dosyanýn parse edilip programýn hizmetine sunulduðu utils sýnýfýdýr.
-FileUtils: Dosya okuma ve yazma iþlemlerinin olduðu utils sýnýfýdýr.
-GameUtils: Oyunu baþlatan ve oyun içindeki iþlemleri yapan utils sýnýfýdýr.
