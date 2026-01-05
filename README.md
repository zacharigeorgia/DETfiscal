myproipologismos

Μία εφαρμογή για πολίτες, αλλά και υπουργούς/ πολιτικούς η οποία επιτρέπει την επισκόπηση και την επεξεργασία του κρατικού προϋπολογισμού για το έτος 2025.

ΠΕΡΙΓΡΑΦΗ
Η εφαρμογή μας αποσκοπεί στην ανάλυση των εσόδων, εξόδων και των κρίσιμων δημοσιονομικών δεικτών. Ο χρήστης έχει τη δυνατότητα, επίσης, να συγκρίνει τα μακροοικονομικά στοιχεία της χώρας με τα αντίστοιχα στοιχεία άλλων ευρωπαϊκών χωρών, όπως η Γερμανία, η Βουλγαρία, η Ιταλία και η Σερβία, καθώς και με τα αντίστοιχα στοιχεία της Ελλάδας του προηγούμενου έτους (2024).Μέσω μηνυμάτων που εξηγούν την οικονομική σημασία του κάθε δείκτη καθίσταται φανερό ποια χώρα βρίσκεται σε καλύτερη θέση, ή αν αντίστοιχα υπήρξε πρόοδος σε σχέση με το 2024. Υποστηρίζεται, επιπλέον, η εκτέλεση "what-if" σεναρίων, δηλαδή η εισαγωγή αλλαγών στα στοιχεία του προϋπολογισμού από τον χρήστη, με σκοπό την παρατήρηση που αυτή η αλλαγή θα επιφέρει στον συνολικό προϋπολογισμό. Η ανάλυση των υπουργείων, επιπλέον, που παρέχει η εφαρμογή συμβάλλει στη διαμόρφωση εικόνας για την κατανομή του δημόσιου χρήματος. Το πρόγραμμα υποστηρίζει, τέλος, την εμφάνιση των στατιστικών σε γραφήμα, τη γραφική διεπαφή, ώστε να καθιστά εφικτή τη διαδραστικη ανάλυση.

Τεχνολογίες και Βιβλιοθήκες
1. Java 20
2. Maven
3. JFreeChart
4. Jackson Databind
5. JUnit 5 
6. Mockito
7. JaCoCo
8. Checkstyle

Dependencies/ Προαπαιτούμενα

Java SDK 20 ή νεότερο
Apache Maven

Εγκατάσταση Maven
Σε περίπτωση που δεν έχετε εγκατεστημένο το Maven:
1. Μεταβείτε στον ιστότοπο:https://maven.apache.org/download.cgi 
2. Κατεβάστε το αρχείο "απαψηε-μαωεν-3.9.12-bin.zip"
3. Αποσυμπιέστε το αρχείο 

Λήψη του Προγράμματος
Κατεβάστε την εργασία από το GitHub

Εκτέλεση Προγράμματος

1. Μεταγλώτισση
Αφού είστε βέβαιοι ότι έχει εγκατασταθεί το Maven εκτελέστε την εντολή "mvn clean install" στο directory του project.

Αποτέλεσμα θα είναι η δημιουργία του αρχείου .jar στο φάκελο target/ και η λήψη όλων των απαραίτητων βιβλιοθηκών.

2. Εκτελέστε την εφαρμογή χρησιμοποιώντας το Maven με την εντολή:
mvn exec:java -Dexec.mainClass="gr.aueb.dmst.detFiscal.Menu"


Εκδόσεις
version 1.3: τρέχουσα έκδοση, προσθήκη README.md και τεχνικής αναφοράς.
version 1.2: Τελικές διορθώσεις σε κάθε κλάση και σύνδεση front-end με back-end.
versin 1.1: Υλοποίηση λειτουργιών (what if scenarios, συγκρίσεις με Ευρωπαϊκές Χώρες και ανά έτη), test για κάθε κλάση και του front-end developing, δημηιουργία γραφικών
version 1.0: Δημιουργία δομής maven, κλάσεων, και προσθήκη JSON αρχείου

Δημιουργοί

Η εφαρμογή αυτή αποτελεί δημιούργημα της ομάδας detFiscal στο πλαίσιο του μαθήματος Προγραμματισμός ΙΙ, ΔΕΤ, ΟΠΑ.
Κρουσταλάκης Δημήτρης,  Project Manager, Back End Developer, τεχνική αναφορά, dimkroustalakis (https://github.com/dimkroustalakis)
Σαραφίδου Νίκη, Presentation Designer, Frond End Developer, nikisarafidou (https://github.com/nikisarafidou)
Κυριακοπούλου Ελπίδα, Back End &  Database Developer, ElpidaKyriakopoulou (https://github.com/ElpidaKyriakopoulou)
Τζιούφα Μιράντα, Back End Developer, Maven Supervisor, Presentation Designer, README.md, mirandatzioufa (https://github.com/mirandatzioufa)
Αγγελίδου Ευαγγελία, Front End Developer, evangeliaangelidou (https://github.com/evangeliaangelidou)
Ζαχάρη Γεωργία, Back End Developer, Maven Supervisor, zacharigeorgia (https://github.com/zacharigeorgia)
Πολυχρονόπουλος Μάριος, Back End Developer, Mariosfren (https://github.com/Mariosfren)

License
This project is licensed under the MIT License - see the LICENSE.md file for details.
