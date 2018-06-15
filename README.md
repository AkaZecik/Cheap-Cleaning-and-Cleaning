# Cheap-Cleaning-and-Cleaning
Let's clean!
---

Jeżeli chcemy dodać własną muzykę do gry to musi ona być w formacie `wav` oraz mieć 16bit/sample. Jeżeli uruchamiamy aplikację z widoku okienkowego, to powinna się ona znaleźć w folderze o nazwie `music`, który z kolei powinien znajdować się w folderze home użytkownika (`~` na linuxie).

Dystrybucja pracy była równomierna. Każdy z nas wykonał 1/3 projektu. Dodatkowo podzieliliśmy się zadaniami w następujący sposób:
* Jędrzej Kula
  * Kwestie techniczne związane z przetwarzaniem muzyki (np. klasa BMPcalc)
  * Kwestie techniczne związane z mechaniką 'dozwolony ruch' (np. klasa BPMhud)
  * Pomysłodawca aplikacji i logiki stojącej za problemem zależności 'ruch-muzyka' w grze
* Artur Zubilewicz
  * Kwestie związane z GUI (np. ustawienia, przyciski, itp.)
  * Maszyna stanów (klasy w folderach State i ApplicationStates, wykorzystane do implementacji różnych etapów pętli gry oraz pozwalająca na implementację transparentnych stanów gry gracza). Pozwala na łatwe dodawanie nowych stanów dzięki poliformicznej hierarchi klas stanów)
  * Dbanie nad całokształtem struktury kodu (odpowiednie klasy w odpowiednich miejscach i relacji między sobą)
  * Przygotowanie linków do dokumentacji i wybór relewantnych stron na [Wiki projektu](https://github.com/AkaZecik/Cheap-Cleaning-and-Cleaning/wiki)
* Patryk Chełmecki
  * Implementacja pętli gry oraz struktury kodu gracza i mapy (np. klasy Player i Map, umożliwiające łatwe dodawanie mapy za pomocą plików .json)
  * Kwestie związane z renderowaniem aktorów na scenie (gracz, mapa, cele gry, itp.)
  * Wiązanie klas pojawiających się w PlayingState (logika gry 'real-time')
