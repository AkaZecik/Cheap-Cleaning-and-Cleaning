# Cheap-Cleaning-and-Cleaning
Let's clean!
---

Jeżeli chcemy dodać własną muzykę do gry to musi ona być w formacie `wav` oraz mieć 16bit/sample. Jeżeli uruchamiamy aplikację z widoku okienkowego, to powinna się ona znaleźć w folderze o nazwie `music`, który z kolei powinien znajdować się w folderze home użytkownika (`~` na linuxie). Natomiast jak uruchamiamy z terminala, to musimy podać mu ścieżkę do `~`, albo umieścić własne utwory w folderze `music` równoległym do pliku `.jar`.

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

### Napotkane problemy i ewentualne rozwiązania
Naszym pierwszym frameworkiem było `JavaFX`, które po pewnym czasie okazało się nieodpowiednim dla naszej aplikacji, ponieważ mocno ograniczało dostępne rozszerzenia dla plików muzycznych, graficznych i video.
Następnie wybraliśmy nasz docelowy framework jako `libGDX`. Framework ten jest dedykowany do pisania gier w Javie, lecz niestety jego dokumentacja jest bardzo okrojona i często funkcje są opisane zaledwie kilkoma niewiele tłumaczącymi słowami. Z tego powodu wielokrotnie musieliśmy szukać gotowych aplikacji w tym frameworku i na nich się wzorować oraz dedukować działanie konkretnych funkcji.

Nasza gra jest grą w czasie rzeczywistym, dlatego wymaga innego podejścia do samej implementacji aplikacji. W kodzie powinna się znaleźć pętla gry, a już sam wybór odpowiedniej pętli gry pod konkretną aplikację wymaga nabycia wcześniejszego doświadczenia. Poświęciliśmy dużo czasu na czytanie książek i artykułów o pętli gry i specyfikacji `event-driven architecture`.

Nauczyliśmy się w ten sposób, że dobrze przygotowana gra (a dokładniej jej silnik) korzysta z `finite state machine` albo `pushdown automata` do realizacji stanów aplikacji i aktorów w grze. Finite state machine jest przydatne jedynie wtedy, gdy dany aktor (lub np. aplikacja) może w danym momencie być tylko w jednym stanie. Oznacza to, że nadaje się ona do użycia w przypadku aktorów o prostym zachowaniu. Ponadto nie udostępnia ona 'pamiętania' więcej niż jednego poprzedniego stanu, więc nie przydaje się do implementacji wielopoziomowego menu, albo postaci, która mogła wcześniej np. 'trzymać przedmiot', potem 'podskoczyć' i w czasie skoku wykonać jeszcze inną operację. Do tego właśnie celu jest `pushdown automata` czyli inaczej mówiąc `stos stanów`.

Kolejnym problemem było ustalenie kiedy gracz może wykonać ruch. Po przeszukaniu materiałów na temat innych gier rozwiązujących podobne problemy zdecydowaliśmy się na wykorzystanie wartości `BPM` (bits per minute), co dodatkowo niosło za sobą dwa kolejne problemy: jak dostarczyć BPM do aplikacji oraz jak podzielić czas na interwały odpowiednio stwierdzające czy ruch jest dozwolony czy nie.
Pierwszy z tych problemów rozwiązaliśmy przez hardkodowanie części utworów oraz implementacja algorytmu liczącego BPM w oparciu o znalezione w internecie materiały.
Drugi z tych problemów rozwiązaliśmy poprzez utworzenie osobnego wątku zmieniającego informację o dostępności ruchu co ustalony fragment czasu. Powodowało to problem związany z równoległą synchronizacją wątków utworzonych przez nas i wątków kontrolowanych przez framework `libGDX`. Ograniczenie narzucone na nas przez ten framework oraz implementacja algorytmu wymagają od nas dostarczania utworów o bardzo zawężonej specyfikacji, tj. utwór musi mieć `16 bits per sample`, powinien mieć częstotliwość `44100 Hz` oraz mieć ścieżkę stereo.
