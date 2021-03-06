package nu.aron.katabankocr;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.collection.Array.collector;

import static java.nio.file.Files.lines;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.collection.Array;
import io.vavr.collection.CharSeq;
import io.vavr.control.Try;

/**
 * Entry point
 *
 */
public class Main { // NOSONAR
    static final Logger log = LoggerFactory.getLogger(Main.class);

    static String readResultFor(Path pathName) {
        var row = readRowsFromFile(pathName);
        Bars firstPosition = findBars(row, 1, Tuple.of(0, 1, 2), Tuple.of(0, 1, 2));
        Bars secondPosition = findBars(row, 4, Tuple.of(3, 4, 5), Tuple.of(3, 4, 5));
        Bars thirdPosition = findBars(row, 7, Tuple.of(6, 7, 8), Tuple.of(6, 7, 8));
        Bars fourthPosition = findBars(row, 10, Tuple.of(9, 10, 11), Tuple.of(9, 10, 11));
        Bars fifthPosition = findBars(row, 13, Tuple.of(12, 13, 14), Tuple.of(12, 13, 14));
        Bars sixthPosition = findBars(row, 16, Tuple.of(15, 16, 17), Tuple.of(15, 16, 17));
        Bars seventhPosition = findBars(row, 19, Tuple.of(18, 19, 20), Tuple.of(18, 19, 20));
        Bars eighthPosition = findBars(row, 22, Tuple.of(21, 22, 23), Tuple.of(21, 22, 23));
        Bars ninthPosition = findBars(row, 25, Tuple.of(24, 25, 26), Tuple.of(24, 25, 26));
        return barMatcher(firstPosition) + barMatcher(secondPosition) + barMatcher(thirdPosition)
                + barMatcher(fourthPosition) + barMatcher(fifthPosition) +
                barMatcher(sixthPosition) + barMatcher(seventhPosition) + barMatcher(eighthPosition)
                + barMatcher(ninthPosition);
    }

    private static Tuple3<CharSeq, CharSeq, CharSeq> readRowsFromFile(Path pathName) {
        Array<String> allRows = Try.of(() -> lines(pathName).collect(collector())).get();
        return Tuple.of(CharSeq.of(allRows.get(0)), CharSeq.of(allRows.get(1)), CharSeq.of(allRows.get(2)));
    }

    private static Bars findBars(Tuple3<CharSeq, CharSeq, CharSeq> row, int first,
            Tuple3<Integer, Integer, Integer> second, Tuple3<Integer, Integer, Integer> third) {
        boolean top = Match(row._1.get(first)).of(Case($('_'), true), Case($(), false));
        boolean upLeft = Match(row._2.get(second._1)).of(Case($('|'), true), Case($(), false));
        boolean middle = Match(row._2.get(second._2)).of(Case($('_'), true), Case($(), false));
        boolean upRight = Match(row._2.get(second._3)).of(Case($('|'), true), Case($(), false));
        boolean downLeft = Match(row._3.get(third._1)).of(Case($('|'), true), Case($(), false));
        boolean bottom = Match(row._3.get(third._2)).of(Case($('_'), true), Case($(), false));
        boolean downRight = Match(row._3.get(third._3)).of(Case($('|'), true), Case($(), false));
        return new Bars(top, upLeft, middle, upRight, downLeft, bottom, downRight);
    }

    private static String barMatcher(Bars bars) {
        Bars zero = new Bars(true, true, false, true, true, true, true);
        Bars one = new Bars(false, false, false, true, false, false, true);
        Bars two = new Bars(true, false, true, true, true, true, false);
        Bars three = new Bars(true, false, true, true, false, true, true);
        Bars four = new Bars(false, true, true, true, false, false, true);
        Bars five = new Bars(true, true, true, false, false, true, true);
        Bars six = new Bars(true, true, true, false, true, true, true);
        Bars seven = new Bars(true, false, false, true, false, false, true);
        Bars eight = new Bars(true, true, true, true, true, true, true);
        Bars nine = new Bars(true, true, true, true, false, true, true);
        return Match(bars).of(
                Case($(zero), "0"),
                Case($(one), "1"),
                Case($(two), "2"),
                Case($(three), "3"),
                Case($(four), "4"),
                Case($(five), "5"),
                Case($(six), "6"),
                Case($(seven), "7"),
                Case($(eight), "8"),
                Case($(nine), "9"),
                Case($(), ""));
    }
}
