Kroki do uruchomienia projektu:
1. Pobrac projekt do lokalnego katalogu na swoim dysku.
2. Otworzyc projekt w swoim IDE np. Intelij Idea.
3. Zbudowac projekt za pomoca komendy mvn clean install w terminalu w IDE.
4. Kliknac prawym przyciskiem na plik "JobTestApplication" w strukturze przegladania plikow projektu i wybrac polecenie
"Run JobTestApplication..."
5. Aplikacja uruchomi sie domyslnie na porcie 8080 i bedzie uzywala bazy danych h2, czyli takiej w pamieci (nie trzeba
instalowac dodatkowej bazy poza aplikacja)
6. Zalogowac sie do consoli bazy danych aplikacji mozna za pomoca linku w przegladarce: http://localhost:8080/h2-console
dane do logowania sa podane przy kazdym nowym uruchomieniu aplikacji w srodowisku IDE, przykladowy komunikat jakiego nalezy
szukac to "H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:b6737481-7ff9-4873-884e-8ad3363c0e31'".
Przy kazdym uruchomieniu dane do logowania beda sie zmieniac, wiec trzeba je kazdorazowo odszukac i wpisac w konsole.
7. Aplikacja jest juz uruchomiona i mozna wysylac do niej requesty.