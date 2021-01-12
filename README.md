# Tema2SE

## Ce este?

  Acest proiect este o aplicatie de tip MAVEN ce foloseste tehnologia Java FX pentru afisarea unei interfete grafice ce prezinta date meteo curente despre un oras ales de utilizator dintr-o lista.
  
## Structura

  Proiectul este de tip MAVEN:
  
  - Folderul resources ce contine :
    - init.json - fisierul de initializare al aplicatiei
    - interface.fxml - fisierul de descriere a interfetei grafice
  - Sursele proiectului
    - Main.java - punctul de intrare al aplicatiei
    - Properties.java - clasa cu variabile statice ce contine constantele importante din aplicatie
    - interface.fxml - fisier XML ce descrie interfata grafica
    - package-ul controllers - ce contine clasa *Controller* care scripteaza UI-ul
    - package-ul data - ce contine clasele *City* si *Forecase*, clase ce contin datele de oraselor si ale procnozei meteo
    - package-ul handlers - ce contine clase de tip *EventHandler<MouseEvent>* pentru evenimentele de click asupra listelor de orase si tari
    - package-ul log - ce contine clasa statica de *LogHistory* pentru logarea datelor meteo afisate
    - package-ul openWeatherAPI - ce contine clasa *Query* pentru interogarea API-ului openWeatherAPI
    - test - folder special ce contine clasele de testare ale aplicatiei
  - Fisiere specifice IDE-ului IntelliJ
  - Folderul de diagrame UML
    - Diagrama de clase
    - Diagrama de stare
  - Tests - folderul cu teste unitare
    - QueryTests - Clasa ce testeaza clasa Query
  - Folderul logs
    - history.json - fisier de logs ce contine istoricul vizualizarilor meteo din aplicatie
  
 ## Dependinte utilizate
 
  Proiectul foloseste urmatoarele dependinte, descrise in POM.xml
  
  - Librariile *JavaFX*
  - Libraria *javaTuples*
  - JUNIT 4.12
  - Mockito
  - Org.json
  
## Fluxul aplicatiei

  1. La pornire, aplicatia incepe prin a crea interfata. In acest sens, ea citeste fisierul *init.json* si incarca toate datele in memorie.
  2. Dupa citirea datelor, se populeaza intern *ListView-ul* cu tari pe baza acestora, iar apoi se ataseaza handle-urile celor 2 liste (cea de tari si cea de orase)
  3. Se afiseaza interfata
  4. Se alege o tara din lista de tari
  5. La alegerea unei tari, se cauta toate orasele din tara respectiva si se afiseaza in lista de orase
  6. La alegerea unui oras din lista, se executa o cerere HTTP catre *openWeatherAPI* pentru a primii datele
  7. La primirea datelor, se afiseaza in tabel si se adauga in fisierul *history.json*
