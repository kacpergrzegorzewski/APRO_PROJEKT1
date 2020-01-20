package nonogramy.entity;

import nonogramy.Settings;

import java.util.*;
import static java.util.Arrays.*;
import static java.util.stream.Collectors.toList;

/**
 * Klasa umozliwiajaca rozwiazanie nonogramu przez algorytm
 */
public class Solver {
    //private String[] p; //Tablica ciągu liter dawana do algorytmu

    /**
     * Konstruktor
     */
    public Solver() {
        //p = prepareArray(board.getRowNumbers(), board.getColsNumbers()); //Przypisuje tablicy liter litery wcześniej zamienione przez funkcję prepareArray
        //newPuzzle(p, board); //Rysuje rozwiązanie
    }

    /**
     * Rozwiazuje i wyswietla plansze
     */
    public Tile[] newPuzzle(Board board) {
        String[] d = prepareArray(board.getRowNumbers(), board.getColsNumbers());
        Tile[] solvedTiles = new Tile[board.tiles.length];

        //Oddziela poszczególne ciągi znaków względem spacji
        String[] rowData = d[0].split("\\s");
        String[] colData = d[1].split("\\s");

        List<List<BitSet>> cols, rows; //Tworzy dwie listy listy bitów

        rows = getCandidates(rowData, colData.length); //Przypisuje możliwe indeksy istnienia jedynke w wierszu
        cols = getCandidates(colData, rowData.length); //Przypisuje możliwe indeksy istnienia jedynke w kolumnie

        int numChanged;
        //Robi cały czas usówanie niepasujących bloków, do momentu, ąż rozwiązania będą się pokrywać i nie będzie innych możliwych
        do {
            numChanged = reduceMutual(cols, rows);
            if (numChanged == -1) {
                System.err.println("No solution");
                break;
            }
        } while (numChanged > 0);

        //Rysuje planszę, w zależności od istnienia 1, albo 0 na danym indeksie
        for (List<BitSet> row : rows) {
            for (int i = 0; i < cols.size(); i++) {
                solvedTiles[i] = new Tile(i% Settings.getBoardSize(), i/Settings.getBoardSize(), row.get(0).get(i));
                System.out.print(row.get(0).get(i) ? "# " : ". ");
            }
            System.out.println();
        }
        System.out.println();

        return solvedTiles;
    }

    /**
     * Zbiera wszystkie rozwiazania wiersza/kolumny i je zwraca
     * @param data wartosci w literach poszczegolnych zamalowanych blokow na planszy
     * @param len ilosc zamalowanych blokiow w wierszu/kolumnie
     * @return wszystkie mozliwe rozwiazania wiersza/kolumny
     */
    private List<List<BitSet>> getCandidates(String[] data, int len) {
        List<List<BitSet>> result = new ArrayList<>();

        //Dla każdego ciągu znaków...
        for (String s : data) {
            List<BitSet> lst = new LinkedList<>(); //Tworzy listę bitów

            int sumChars = s.chars().map(c -> c - 'A' + 1).sum(); //Zamienia literki na cyfry i sumuje je

            //Dla każdego elementu w ciągu znaków zamienia je odpowiednio na konkretnee liczby
            List<String> prep = stream(s.split("")).map(x -> repeat(x.charAt(0) - 'A' + 1, "1")).collect(toList());

            //Dla każdego możliwego rozwiązania
            for (String r : genSequence(prep, len - sumChars + 1)) {
                char[] bits = r.substring(1).toCharArray(); //Oddziela kolejne znaki w ciągu
                BitSet bitset = new BitSet(bits.length);
                //Sprawdza gdzie stoją jedynki i indeksuje ich pozycje i dodaje do listy
                for (int i = 0; i < bits.length; i++)
                    bitset.set(i, bits[i] == '1');
                lst.add(bitset);
            }
            //Dodaje liste do listy list
            result.add(lst);
        }
        return result;
    }


    /**
     * Generuje kolejne mozliwosci rozwiazan dla wiersza/kolumny i zwraca ich tablice
     * @param ones lista jedynke w wierszu/kolumnie
     * @param numZeros liczba zer w wierszy/kolumnie
     * @return sekwencja zer i jedynek reprezentujacych mozliwe rozwiazania
     */
    private List<String> genSequence(List<String> ones, int numZeros) {
        //Jeżeli lista jedynek jest pusta to zwraca zera
        if (ones.isEmpty())
            return asList(repeat(numZeros, "0"));

        List<String> result = new ArrayList<>();
        //Dla wszystkich zer znajdujących się w wierszy/kolumnie...
        for (int x = 1; x < numZeros - ones.size() + 2; x++) {
            List<String> skipOne = ones.stream().skip(1).collect(toList()); //Oddziela bloki zamalowane od siebie
            //Odejmuje x od ilości zer i jescze raz wywołuje funkcję
            for (String tail : genSequence(skipOne, numZeros - x))
                result.add(repeat(x, "0") + ones.get(0) + tail);
        }
        return result;
    }

    /**
     * Tworzy ciag zer i jedynek
     * @param n dlugosc ciagu
     * @param s znak z ktorego sklada sie ciag
     * @return gotowy ciag znakow
     */
    private String repeat(int n, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            sb.append(s);
        return sb.toString();
    }

    /*Jeżeli wszyskie możliwe indesky jedynek w rozwiązaniach są takie same dla każdego
    pola, wtedy to pole jest zaznaczane, tak samo w wierszach jak i w kolumnach
    Sprawdza do dla każdego wiersza i kolumny, wtedy kiedy nie będzie już możliwości odrzycenia rozwiązania
    lub lista będzie pusta. Następnie zwraca liczbę odrzuconych pol które nie nalezały do rozwiązania */

    /**
     * Usowa wszyskie niepasujace pola
     * @param cols lista list zer i jedynek w kolumnie
     * @param rows lista list zer i jedynek w wierszu
     * @return sume usunietych zlych pol z dwoch iteracji
     */
    private int reduceMutual(List<List<BitSet>> cols, List<List<BitSet>> rows) {
        int countRemoved1 = reduce(cols, rows);
        if (countRemoved1 == -1)
            return -1;

        int countRemoved2 = reduce(rows, cols);
        if (countRemoved2 == -1)
            return -1;

        return countRemoved1 + countRemoved2;
    }

    /**
     * Usowa te pola z rozwiazania, ktore nie beda pasowac, zwraca ich liczbe
     * @param a lista list zer i jedynek w kolumnie
     * @param b lista list zer i jedynek w wierszu
     * @return liczbe usunietych zlych pol
     */
    private int reduce(List<List<BitSet>> a, List<List<BitSet>> b) {
        int countRemoved = 0;

        for (int i = 0; i < a.size(); i++) {

            BitSet commonOn = new BitSet();
            commonOn.set(0, b.size());
            BitSet commonOff = new BitSet();

            //Sprawdza, które wartości w rzędach dla każdego rozwiązania sa wspólne
            for (BitSet candidate : a.get(i)) {
                commonOn.and(candidate);
                commonOff.or(candidate);
            }

            //Usówa z kolumn te wartości, które na pewno nie będą się łączyć z wierszami
            for (int j = 0; j < b.size(); j++) {
                final int fi = i, fj = j;

                if (b.get(j).removeIf(cnd -> (commonOn.get(fj) && !cnd.get(fi))
                        || (!commonOff.get(fj) && cnd.get(fi))))
                    countRemoved++;

                if (b.get(j).isEmpty())
                    return -1;
            }
        }
        return countRemoved;
    }

    /**
     * Zamienia liczby w tablicach na odpowiednie litery
     * @param rows tablica tablic numerow w rzedach
     * @param cols tablica tablic numerow w kolumnach
     * @return zamieniona odpowiednio na litery tablice z ciagami znakow
     */
    public String[] prepareArray(ArrayList<ArrayList<Integer>> rows, ArrayList<ArrayList<Integer>> cols) {
        StringBuilder r = new StringBuilder();
        StringBuilder c = new StringBuilder();

        String[] newArray = new String[2];

        for (ArrayList<Integer> block : rows) {
            for (int n : block) {
                char letter = (char)(n + 64);
                r.append(letter);
            }
            r.append(' ');
        }

        for (ArrayList<Integer> block : cols) {
            for (int n : block) {
                char letter = (char)(n + 64);
                c.append(letter);
            }
            c.append(' ');
        }

        newArray[0] = r.toString();
        newArray[1] = c.toString();

        return newArray;
    }
}
