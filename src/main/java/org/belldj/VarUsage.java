package org.belldj;

import org.graalvm.compiler.lir.Variable;

import uk.gov.gchq.palisade.service.palisade.model.AuditablePalisadeRequest;
import uk.gov.gchq.palisade.service.palisade.model.TokenRequestPair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class VarUsage {

    public VarUsage() {
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("unused")
    public void go() throws Exception {

        /*
         * This is not particularly terrible, but it is somewhat redundant. And while
         * IDEs can help a lot with writing such code, readability suffers when variable
         * names jump around a lot because their types have very different character
         * counts or when developers avoid declaring intermediate variables because type
         * declarations would eat up a lot of attention without adding much value.
         */
        URL codefx = new URL("https://nipafx.dev");
        URLConnection connection = codefx.openConnection();
        Reader reader = new BufferedReader(
            new InputStreamReader(connection.getInputStream()));

        /*
         * As you can see, this saves a few characters when typing, but more importantly
         * it deduplicates redundant information and neatly aligns the variable's names,
         * which eases reading them.
         */
        var codefx1 = new URL("https://nipafx.dev");
        var connection1 = codefx1.openConnection();
        var reader1 = new BufferedReader(
            new InputStreamReader(connection.getInputStream()));

        // var in for loops
        var numbers = List.of("a", "b", "c");
        for (var nr : numbers) {
            System.out.print(nr + " ");
        }
        for (var i = 0; i < numbers.size(); i++) {
            System.out.print(numbers.get(i) + " ");
        }

        // var in try-with-resources

        try (var fis = new FileInputStream(new File("no-such-file"))) {
            new BufferedReader(new InputStreamReader(fis))
                .lines()
                .forEach(System.out::println);
        } catch (IOException ex) {
            // at least, we tried
            System.out.println("There's actually no `no-such-file`. :)");
        }

        /*
         * Declarations can be quite ugly, particularly if enterprise-grade class names
         * and generics are involved.
         */

        InternationalCustomerOrderProcessor<AnonymousCustomer, SimpleOrder<Book>> orderProcessor = createInternationalOrderProcessor(customer, order);

        /*
         * There's a long-ass type name, which pushes the variable name most of the way
         * to the end of the line and leaves you with either bumping line length to 150
         * chars or initializing the variable on a new line. Both options suck if you're
         * aiming for readability.
         */

        var orderProcessor = createInternationalOrderProcessor(customer, order);

        /*
         * With var it is much less burdensome and easier on the eye to declare
         * intermediate variables and we might end up doing that in places where we
         * didn't before. Think about nested or chained expressions where you decided
         * against breaking them apart because the reduction in complexity got eaten by
         * the increase in ceremony. Judicious use of var can make intermediate results
         * more obvious and more easily accessible.
         */

        /*
         * Readability
         */

        // with explicit types
        No no = new No();
        AmountIncrease<BigDecimal> more = new BigDecimalAmountIncrease();
        HorizontalConnection<LinePosition, LinePosition> jumping = new HorizontalLinePositionConnection();
        Variable variable = new Constant(5);
        List<String> names = List.of("Max", "Maria");

        // with inferred types
        var no = new No();
        var more = new BigDecimalAmountIncrease();
        var jumping = new HorizontalLinePositionConnection();
        var variable = new Constant(5);
        var names = List.of("Max", "Maria");

        /*
         * As pointed out above, the other improvement to readability can come from
         * having more intermediate variables declared because it comes at a cheaper
         * cost when writing and reading.
         */

        /**
         * Brian Goetz, Java language architect at Oracle and in charge of Project
         * Amber, gave a first heuristic: | Use the var construct when it makes the code
         * | clearer and more concise and you're not loosing | essential information.
         */

        // For null initialisation you must specify the type:

        String s1 = null;
        var s2 = (String) null;

        // this would all depend if you going for readability. If s2 was being assigned
        // in a group then the cast solution may be preferable for readability.

        /*
         * Another example
         */

        AuditablePalisadeRequest auditableRequest = AuditablePalisadeRequest.Builder.create().withPalisadeRequest(request);
        TokenRequestPair requestPair = new TokenRequestPair(token, auditableRequest);

        var auditableRequest = AuditablePalisadeRequest.Builder.create().withPalisadeRequest(request);
        var requestPair = new TokenRequestPair(token, auditableRequest);

        /*
         * The types are still readily availablke to see for the develope, but it's much
         * easier to read.
         */

        // and another

        final AtomicBoolean observedStart = new AtomicBoolean(false);
        final AtomicBoolean observedResource = new AtomicBoolean(false);

        var observedStart = new AtomicBoolean(false);
        var observedResource = new AtomicBoolean(false);

        // the compiler treats them as "effectively" final :)

        // less code to read is good imo.

    }

}
