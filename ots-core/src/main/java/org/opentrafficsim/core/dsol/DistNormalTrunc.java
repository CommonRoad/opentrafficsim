package org.opentrafficsim.core.dsol;

import java.util.Locale;

import nl.tudelft.simulation.jstats.distributions.DistContinuous;
import nl.tudelft.simulation.jstats.streams.MersenneTwister;
import nl.tudelft.simulation.jstats.streams.StreamInterface;

/**
 * The Normal Truncated distribution. For more information on the normal distribution see
 * <a href="http://mathworld.wolfram.com/NormalDistribution.html"> http://mathworld.wolfram.com/NormalDistribution.html </a>
 * <p>
 * This version of the normal distribution uses the numerically approached inverse cumulative distribution.
 * <p>
 * (c) copyright 2002-2018 <a href="http://www.simulation.tudelft.nl">Delft University of Technology </a>, the Netherlands. <br>
 * See for project information <a href="http://www.simulation.tudelft.nl"> www.simulation.tudelft.nl </a> <br>
 * License of use: <a href="http://www.gnu.org/copyleft/lesser.html">Lesser General Public License (LGPL) </a>, no warranty.
 * @author <a href="mailto:a.verbraeck@tudelft.nl"> Alexander Verbraeck </a> <br>
 *         <a href="http://www.transport.citg.tudelft.nl">Wouter Schakel</a>
 */
public class DistNormalTrunc extends DistContinuous
{
    /** */
    private static final long serialVersionUID = 1L;

    /** mu refers to the mean of the normal distribution. */
    private final double mu;

    /** mu refers to the mean of the normal distribution. */
    private final double sigma;

    /** minimum x-value of the distribution. */
    private final double min;

    /** maximum x-value of the distribution. */
    private final double max;

    /** cumulative distribution value of the minimum. */
    private final double cumulMin;

    /** cumulative distribution value difference between max and min. */
    private final double cumulDiff;

    /** factor on probability density to normalize to 1. */
    private final double probDensFactor;

    /**
     * constructs a normal distribution with mu=0 and sigma=1. Errors of various types, e.g., in the impact point of a bomb;
     * quantities that are the sum of a large number of other quantities by the virtue of the central limit theorem.
     * @param stream the numberstream
     * @param min minimum x-value of the distribution
     * @param max maximum x-value of the distribution
     */
    public DistNormalTrunc(final StreamInterface stream, final double min, final double max)
    {
        this(stream, 0.0, 1.0, min, max);
    }

    /**
     * constructs a normal distribution with mu and sigma.
     * @param stream the numberstream
     * @param mu the medium
     * @param sigma the standard deviation
     * @param min minimum x-value of the distribution
     * @param max maximum x-value of the distribution
     */
    public DistNormalTrunc(final StreamInterface stream, final double mu, final double sigma, final double min,
            final double max)
    {
        super(stream);
        if (max < min)
        {
            throw new IllegalArgumentException("Error Normal Truncated - max < min");
        }
        this.mu = mu;
        this.sigma = sigma;
        this.min = min;
        this.max = max;
        this.cumulMin = getCumulativeProbabilityNotTruncated(min);
        this.cumulDiff = getCumulativeProbabilityNotTruncated(max) - this.cumulMin;
        this.probDensFactor = 1.0 / this.cumulDiff;
    }

    /** {@inheritDoc} */
    @Override
    public double draw()
    {
        return getInverseCumulativeProbabilityNotTruncated(this.cumulMin + this.cumulDiff * this.stream.nextDouble());
    }

    /**
     * returns the cumulative probability of the x-value.
     * @param x the observation x
     * @return double the cumulative probability
     */
    public double getCumulativeProbability(final double x)
    {
        if (x < this.min)
        {
            return 0.0;
        }
        if (x > this.max)
        {
            return 1.0;
        }
        return (getCumulativeProbabilityNotTruncated(x) - this.cumulMin) * this.probDensFactor;
    }

    /**
     * returns the cumulative probability of the x-value.
     * @param x the observation x
     * @return double the cumulative probability
     */
    private double getCumulativeProbabilityNotTruncated(final double x)
    {
        double z = (x - this.mu) / this.sigma * 100;
        double absZ = Math.abs(z);
        int intZ = (int) absZ;
        double f = 0.0;
        if (intZ >= 1000)
        {
            intZ = 999;
            f = 1.0;
        }
        else
        {
            f = absZ - intZ;
        }
        if (z >= 0)
        {
            return (1 - f) * CUMULATIVE_NORMAL_PROPABILITIES[intZ] + f * CUMULATIVE_NORMAL_PROPABILITIES[intZ + 1];
        }
        return 1 - ((1 - f) * CUMULATIVE_NORMAL_PROPABILITIES[intZ] + f * CUMULATIVE_NORMAL_PROPABILITIES[intZ + 1]);
    }

    /**
     * returns the x-value of the given cumulativePropability.
     * @param cumulativeProbability reflects cum prob
     * @return double the inverse cumulative probability
     */
    public double getInverseCumulativeProbability(final double cumulativeProbability)
    {
        if (cumulativeProbability < 0 || cumulativeProbability > 1)
        {
            throw new IllegalArgumentException("1<cumulativeProbability<0 ?");
        }
        /*
         * For extreme cases we return the min and max directly. The method getInverseCumulativeProbabilityNotTruncated() can
         * only return values from "mu - 10*sigma" to "mu + 10*sigma". If min or max is beyond these values, those values will
         * result. For any cumulative probability that is slightly above 0.0 or slightly below 1.0, values in the range from
         * "mu - 10*sigma" to "mu + 10*sigma" will always result.
         */
        if (cumulativeProbability == 0.0)
        {
            return this.min;
        }
        if (cumulativeProbability == 1.0)
        {
            return this.max;
        }
        return getInverseCumulativeProbabilityNotTruncated(this.cumulMin + cumulativeProbability * this.cumulDiff);
    }

    /** {@inheritDoc} */
    @Override
    public double probDensity(final double x)
    {
        if (x < this.min || x > this.max)
        {
            return 0.0;
        }
        return this.probDensFactor / (Math.sqrt(2 * Math.PI * Math.pow(this.sigma, 2)))
                * Math.exp(-1 * Math.pow(x - this.mu, 2) / (2 * Math.pow(this.sigma, 2)));
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "NormalTrunc(" + this.mu + "," + this.sigma + "," + this.min + "," + this.max + ")";
    }

    /**
     * Test.
     * @param args args
     */
    public static void main(final String[] args)
    {
        StreamInterface stream = new MersenneTwister();
        double mu = 2.0;
        double sigma = 3.0;
        double min = -5.0;
        double max = 4.0;
        DistNormalTrunc dist = new DistNormalTrunc(stream, mu, sigma, min, max);

        System.out.println("<< probability density >>");
        double sum = 0.0;
        double step = (max - min) / 96;
        for (double x = min - 2 * step; x <= max + 2 * step; x += step)
        {
            double p = dist.probDensity(x);
            System.out.println(String.format(Locale.GERMAN, "%.8f;%.8f", x, p));
            sum += p * step;
        }
        System.out.println(String.format(Locale.GERMAN, "Approx. sum = %.8f", sum));
        System.out.println("");

        System.out.println("<< cumulative density >>");
        for (double x = min - 2 * step; x <= max + 2 * step; x += step)
        {
            double c = dist.getCumulativeProbability(x);
            System.out.println(String.format(Locale.GERMAN, "%.8f;%.8f", x, c));
        }
        System.out.println("");

        System.out.println("<< inverse cumulative density >>");
        for (double c = 0.0; c < 1.005; c += 0.01)
        {
            double x = dist.getInverseCumulativeProbability(Math.min(c, 1.0)); // want to include 1.0, also if 1.0000000000001
            System.out.println(String.format(Locale.GERMAN, "%.8f;%.8f", c, x));
        }
        System.out.println("");

        System.out.println("<< 10000 random numbers. >>");
        for (int i = 1; i < 10000; i++)
        {
            System.out.println(String.format(Locale.GERMAN, "%,8f", dist.draw()));
        }

    }

    /**
     * returns the x-value of the given cumulativePropability.
     * @param cumulativeProbability reflects cum prob
     * @return double the inverse cumulative probability
     */
    private double getInverseCumulativeProbabilityNotTruncated(final double cumulativeProbability)
    {
        if (cumulativeProbability < 0 || cumulativeProbability > 1)
        {
            throw new IllegalArgumentException("1<cumulativeProbability<0 ?");
        }
        boolean located = false;
        double prob = cumulativeProbability;
        if (cumulativeProbability < 0.5)
        {
            prob = 1 - cumulativeProbability;
        }
        int i = 0;
        double f = 0.0;
        while (!located)
        {
            if (CUMULATIVE_NORMAL_PROPABILITIES[i] < prob && CUMULATIVE_NORMAL_PROPABILITIES[i + 1] >= prob)
            {
                located = true;
                if (CUMULATIVE_NORMAL_PROPABILITIES[i] < CUMULATIVE_NORMAL_PROPABILITIES[i + 1])
                {
                    f = (prob - CUMULATIVE_NORMAL_PROPABILITIES[i])
                            / (CUMULATIVE_NORMAL_PROPABILITIES[i + 1] - CUMULATIVE_NORMAL_PROPABILITIES[i]);
                }
            }
            else
            {
                i++;
            }
        }
        if (cumulativeProbability < 0.5)
        {
            return this.mu - ((f + i) / 100.0) * this.sigma;
        }
        return ((f + i) / 100.0) * this.sigma + this.mu;
    }

    /**
     * CUMULATIVE_NORMAL_PROPABILITIES represents the NORMAL DISTRIBUTION FUNCTION TABLE. In order to keep this table as fast as
     * possible no x values are stored. The range of the table is {0.00,0.01,0.02,...,10.00}
     */
    public static final double[] CUMULATIVE_NORMAL_PROPABILITIES = { 0.5000000000000000, 0.5039873616189113, 0.5079763193203305,
            0.5119644795160448, 0.5159514436524734, 0.5199368135347197, 0.5239201914458871, 0.5279011802661332,
            0.5318793835914418, 0.5358544058520341, 0.5398258524303582, 0.5437933297786074, 0.5477564455357087,
            0.5517148086437129, 0.5556680294635363, 0.5596157198900099, 0.5635574934661438, 0.567492965496589,
            0.5714217531602216, 0.5753434756217956, 0.5792577541426178, 0.5831642121901748, 0.5870624755466856,
            0.5909521724164968, 0.5948329335322977, 0.5987043922600851, 0.6025661847028365, 0.6064179498028396,
            0.6102593294426336, 0.6140899685445192, 0.6179095151685767, 0.6217176206091617, 0.6255139394898266,
            0.6292981298566381, 0.6330698532698229, 0.6368287748937326, 0.6405745635850643, 0.644306891979308,
            0.6480254365753887, 0.6517298778184553, 0.6554199001807951, 0.6590951922408244, 0.6627554467601383,
            0.6664003607585898, 0.6700296355873492, 0.6736429769999337, 0.6772400952211824, 0.6808207050141283,
            0.684384525744776, 0.6879312814447339, 0.6914607008716991, 0.6949725175677556, 0.6984664699154933,
            0.7019423011919023, 0.7053997596200493, 0.7088385984185037, 0.7122585758485337, 0.7156594552589977,
            0.719041005128991, 0.722402999108207, 0.7257452160549791, 0.7290674400720636, 0.7323694605400943,
            0.7356510721487322, 0.73891207492553, 0.7421522742624731, 0.7453714809402076, 0.7485695111499924,
            0.7517461865133215, 0.754901334099275, 0.7580347864395746, 0.761146381541351, 0.764235962897659, 0.7673033794957203,
            0.7703484858229172, 0.7733711418705582, 0.7763712131354236, 0.779348570619097, 0.7823030908251122,
            0.7852346557539338, 0.7881431528957866, 0.79102847522134, 0.7938905211703059, 0.7967291946379159,
            0.7995444049593787, 0.8023360668922485, 0.8051041005968205, 0.8078484316145099, 0.810568990844285,
            0.8132657145171739, 0.8159385441688476, 0.8185874266103501, 0.8212123138969823, 0.8238131632953734,
            0.8263899372487721, 0.8289426033406134, 0.831471134256341, 0.8339755077435994, 0.8364557065707554,
            0.8389117184838284, 0.8413435361618438, 0.8437511571706939, 0.8461345839154543, 0.8484938235912971,
            0.8508288881329673, 0.8531397941628848, 0.8554265629379223, 0.8576892202948876, 0.8599277965947512,
            0.8621423266656558, 0.8643328497447642, 0.866499409418988, 0.8686420535645871, 0.8707608342857796,
            0.872855807852312, 0.8749270346360735, 0.8769745790467864, 0.8789985094668413, 0.8809988981852741,
            0.8829758213309686, 0.8849293588050865, 0.886859594212814, 0.8887666147944305, 0.8906505113557653,
            0.8925113781980463, 0.8943493130472426, 0.8961644169828942, 0.8979567943664809, 0.8997265527693831,
            0.9014738029004713, 0.9031986585333613, 0.9049012364333603, 0.9065816562841962, 0.9082400406144879,
            0.9098765147240866, 0.9114912066102397, 0.9130842468937037, 0.9146557687447538, 0.9162059078092082,
            0.9177348021344403, 0.9192425920954638, 0.9207294203210978, 0.9221954316202577, 0.9236407729084056,
            0.9250655931341809, 0.9264700432062887, 0.9278542759206125, 0.9292184458876369, 0.9305627094602053,
            0.9318872246616042, 0.9331921511140636, 0.9344776499676445, 0.9357438838296055, 0.9369910166942079,
            0.9382192138730403, 0.9394286419258764, 0.940619468592069, 0.941791862722541, 0.9429459942123697, 0.944082033934012,
            0.9452001536711674, 0.9463005260533299, 0.9473833244910284, 0.9484487231117875, 0.94949689669682,
            0.9505280206184922, 0.9515422707785594, 0.9525398235471815, 0.9535208557027719, 0.9544855443726583,
            0.9554340669746081, 0.9563666011591815, 0.9572833247529947, 0.9581844157028426, 0.9590700520207252,
            0.9599404117298032, 0.9607956728112474, 0.9616360131520566, 0.9624616104937792, 0.9632726423822178,
            0.9640692861180821, 0.9648517187086078, 0.9656201168201517, 0.9663746567317699, 0.9671155142897785,
            0.9678428648633103, 0.9685568833008597, 0.9692577438878406, 0.9699456203051116, 0.9706206855885555,
            0.971283112089614, 0.9719330714368619, 0.9725707344985791, 0.9731962713463076, 0.973809851219447,
            0.9744116424908279, 0.9750018126333039, 0.9755805281873245, 0.9761479547295168, 0.9767042568422514,
            0.9772495980842009, 0.9777841409618732, 0.9783080469021415, 0.9788214762257182, 0.979324588121612,
            0.9798175406225412, 0.9803004905813041, 0.9807735936480722, 0.981237004248657, 0.9816908755636616,
            0.9821353595085873, 0.9825706067148376, 0.9829967665116119, 0.9834139869087157, 0.9838224145802272,
            0.9842221948490532, 0.9846134716723274, 0.9849963876276845, 0.9853710839003452, 0.985737700271045,
            0.9860963751047681, 0.9864472453402844, 0.9867904464804915, 0.9871261125835228, 0.9874543762546124,
            0.9877753686387415, 0.9880892194139929, 0.988396056785651, 0.9886960074810209, 0.9889891967449299,
            0.9892757483359246, 0.989555784523144, 0.9898294260838525, 0.9900967923016145, 0.9903580009651043,
            0.9906131683675308, 0.9908624093066779, 0.9911058370855164, 0.9913435635134026, 0.991575698907852,
            0.9918023520968361, 0.9920236304216317, 0.9922396397401824, 0.9924504844309792, 0.9926562673974049,
            0.992857090072596, 0.9930530524247368, 0.9932442529628207, 0.9934307887428471, 0.9936127553744406,
            0.993790247027868, 0.9939633564414762, 0.9941321749294971, 0.9942967923902228, 0.9944572973145361,
            0.9946137767947988, 0.9947663165340496, 0.9949150008555357, 0.9950599127125389, 0.9952011336985047,
            0.9953387440574476, 0.9954728226946147, 0.9956034471874149, 0.9957306937965966, 0.9958546374776436,
            0.9959753518924065, 0.9960929094209239, 0.9962073811734615, 0.9963188370027265, 0.9964273455162624,
            0.9965329740889989, 0.9966357888759699, 0.9967358548251734, 0.9968332356905508, 0.9969279940451093,
            0.9970201912941401, 0.9971098876885598, 0.9971971423383441, 0.9972820132260181, 0.9973645572202651,
            0.9974448300895796, 0.9975228865159886, 0.9975987801088081, 0.9976725634184758, 0.9977442879503817,
            0.9978140041787443, 0.9978817615605097, 0.9979476085492539, 0.9980115926090906, 0.9980737602285842,
            0.9981341569346482, 0.9981928273064313, 0.9982498149891729, 0.998305162708049, 0.9983589122819604,
            0.9984111046372987, 0.9984617798216666, 0.9985109770175317, 0.9985587345558365, 0.9986050899295523,
            0.9986500798071501, 0.9986937400460181, 0.9987361057057903, 0.9987772110616016, 0.9988170896172607,
            0.9988557741183267, 0.9988932965651068, 0.9989296882255506, 0.9989649796480435, 0.9989992006741035,
            0.9990323804509681, 0.9990645474440748, 0.999095729449436, 0.999125953605889, 0.9991552464072354,
            0.9991836337142654, 0.9992111407666507, 0.9992377921947238, 0.9992636120311323, 0.9992886237223589,
            0.9993128501401143, 0.9993363135925993, 0.9993590358356453, 0.999381038083711, 0.9994023410207445,
            0.9994229648109153, 0.9994429291092068, 0.9994622530718738, 0.9994809553667513, 0.9994990541834521,
            0.9995165672433891, 0.9995335118096818, 0.9995499046969138, 0.999565762280761, 0.9995811005074615,
            0.9995959349031589, 0.9996102805830935, 0.9996241522606701, 0.9996375642563711, 0.9996505305065254,
            0.9996630645719558, 0.9996751796464766, 0.9996868885652497, 0.9996982038129988, 0.9997091375321072,
            0.999719701530551, 0.9997299072897112, 0.9997397659720517, 0.9997492884286557, 0.9997584852066235,
            0.9997673665563579, 0.9997759424386968, 0.9997842225319191, 0.9997922162386252, 0.9997999326924907,
            0.999807380764881, 0.999814569071358, 0.9998215059780381, 0.9998281996078514, 0.9998346578466425,
            0.9998408883492018, 0.9998468985451213, 0.9998526956445724, 0.999858286643944, 0.9998636783313704,
            0.9998688772921471, 0.9998738899140359, 0.9998787223924446, 0.9998833807355126, 0.9998878707690767,
            0.9998921981415287, 0.9998963683285831, 0.9999003866379228, 0.9999042582137387, 0.99990798804119, 0.999911580950741,
            0.9999150416224173, 0.9999183745899578, 0.9999215842448586, 0.9999246748403525, 0.9999276504952729,
            0.9999305151978185, 0.9999332728092672, 0.9999359270675735, 0.9999384815908634, 0.9999409398808894,
            0.9999433053263691, 0.9999455812062524, 0.9999477706929066, 0.999949876855225, 0.9999519026616563,
            0.9999538509831598, 0.9999557245960793, 0.9999575261849584, 0.9999592583452697, 0.9999609235860767,
            0.9999625243326383, 0.9999640629289291, 0.9999655416401065, 0.9999669626549059, 0.9999683280879766,
            0.9999696399821539, 0.9999709003106689, 0.9999721109793053, 0.9999732738284871, 0.9999743906353185,
            0.9999754631155693, 0.9999764929255975, 0.9999774816642223, 0.9999784308745513, 0.9999793420457469,
            0.9999802166147403, 0.9999810559679307, 0.9999818614427762, 0.9999826343293992, 0.9999833758721115,
            0.9999840872709006, 0.999984769682885, 0.9999854242237042, 0.9999860519689024, 0.9999866539552403,
            0.9999872311819792, 0.9999877846121323, 0.9999883151736708, 0.9999888237607027, 0.9999893112346047,
            0.999989778425137, 0.9999902261314975, 0.9999906551233723, 0.9999910661419341, 0.9999914599008299,
            0.9999918370871163, 0.999992198362175, 0.9999925443626037, 0.9999928757010765, 0.999993192967172,
            0.9999934967281847, 0.9999937875299059, 0.9999940658973759, 0.9999943323356203, 0.9999945873303567,
            0.9999948313486832, 0.9999950648397335, 0.9999952882353275, 0.9999955019505884, 0.9999957063845496,
            0.9999959019207282, 0.9999960889276895, 0.9999962677595902, 0.9999964387567071, 0.9999966022459394,
            0.9999967585412978, 0.9999969079443901, 0.9999970507448684, 0.9999971872208745, 0.9999973176394685,
            0.9999974422570479, 0.9999975613197305, 0.9999976750637555, 0.9999977837158475, 0.9999978874935767,
            0.9999979866057059, 0.9999980812525245, 0.9999981716261707, 0.9999982579109443, 0.9999983402836066,
            0.9999984189136624, 0.999998493963655, 0.9999985655894209, 0.9999986339403611, 0.9999986991596813,
            0.9999987613846415, 0.9999988207467845, 0.9999988773721528, 0.9999989313815221, 0.999998982890599,
            0.9999990320102132, 0.9999990788465284, 0.9999991235012167, 0.9999991660716405, 0.9999992066510266,
            0.99999924532863, 0.9999992821898891, 0.9999993173165908, 0.9999993507870083, 0.999999382676051, 0.9999994130553951,
            0.9999994419936197, 0.9999994695563318, 0.999999495806289, 0.9999995208035128, 0.9999995446054075,
            0.9999995672668669, 0.9999995888403767, 0.9999996093761104, 0.9999996289220386, 0.9999996475240089,
            0.999999665225837, 0.9999996820693978, 0.9999996980947014, 0.9999997133399728, 0.9999997278417341,
            0.9999997416348584, 0.9999997547526686, 0.9999997672269785, 0.999999779088168, 0.9999997903652507,
            0.9999998010859101, 0.9999998112765858, 0.9999998209625084, 0.9999998301677586, 0.999999838915313,
            0.9999998472271051, 0.9999998551240461, 0.9999998626260925, 0.9999998697522797, 0.9999998765207626,
            0.999999882948852, 0.9999998890530569, 0.9999998948491172, 0.9999999003520337, 0.9999999055761172,
            0.9999999105349969, 0.9999999152416649, 0.9999999197085024, 0.9999999239473055, 0.9999999279693159,
            0.9999999317852374, 0.9999999354052702, 0.9999999388391236, 0.9999999420960494, 0.9999999451848542,
            0.9999999481139239, 0.9999999508912378, 0.999999953524401, 0.999999956020643, 0.999999958386847, 0.9999999606295614,
            0.9999999627550202, 0.999999964769152, 0.9999999666775974, 0.9999999684857224, 0.9999999701986259,
            0.9999999718211613, 0.9999999733579436, 0.9999999748133596, 0.9999999761915778, 0.9999999774965675,
            0.9999999787320892, 0.9999999799017258, 0.9999999810088808, 0.9999999820567871, 0.9999999830485156,
            0.9999999839869854, 0.9999999848749658, 0.9999999857150916, 0.999999986509861, 0.9999999872616481,
            0.9999999879727063, 0.9999999886451735, 0.9999999892810812, 0.9999999898823579, 0.9999999904508311,
            0.9999999909882377, 0.9999999914962242, 0.9999999919763545, 0.9999999924301094, 0.9999999928588945,
            0.9999999932640453, 0.9999999936468255, 0.9999999940084324, 0.9999999943500043, 0.9999999946726192,
            0.9999999949772991, 0.9999999952650122, 0.999999995536675, 0.9999999957931576, 0.9999999960352846,
            0.999999996263837, 0.9999999964795528, 0.9999999966831338, 0.9999999968752427, 0.9999999970565085,
            0.9999999972275259, 0.9999999973888581, 0.9999999975410393, 0.9999999976845725, 0.9999999978199366,
            0.9999999979475838, 0.9999999980679412, 0.9999999981814149, 0.999999998288388, 0.9999999983892215,
            0.9999999984842588, 0.9999999985738248, 0.9999999986582248, 0.9999999987377505, 0.9999999988126743,
            0.9999999988832565, 0.9999999989497415, 0.9999999990123608, 0.9999999990713334, 0.9999999991268663,
            0.9999999991791552, 0.9999999992283836, 0.9999999992747264, 0.9999999993183496, 0.9999999993594075,
            0.9999999993980476, 0.9999999994344076, 0.9999999994686192, 0.9999999995008081, 0.9999999995310868,
            0.9999999995595689, 0.999999999586359, 0.9999999996115511, 0.9999999996352417, 0.9999999996575185,
            0.9999999996784616, 0.9999999996981475, 0.9999999997166537, 0.9999999997340464, 0.9999999997503924,
            0.9999999997657535, 0.9999999997801852, 0.9999999997937458, 0.9999999998064829, 0.9999999998184489,
            0.9999999998296857, 0.9999999998402402, 0.9999999998501493, 0.9999999998594546, 0.9999999998681913,
            0.9999999998763954, 0.9999999998840915, 0.9999999998913157, 0.9999999998980964, 0.9999999999044622,
            0.9999999999104311, 0.9999999999160324, 0.9999999999212845, 0.9999999999262145, 0.9999999999308378,
            0.9999999999351717, 0.999999999939236, 0.9999999999430531, 0.9999999999466233, 0.9999999999499747,
            0.9999999999531113, 0.9999999999560638, 0.9999999999588188, 0.9999999999613983, 0.9999999999638182,
            0.9999999999660865, 0.9999999999682123, 0.9999999999702063, 0.99999999997208, 0.9999999999738262,
            0.9999999999754482, 0.9999999999769803, 0.9999999999784355, 0.999999999979752, 0.9999999999810224,
            0.9999999999821866, 0.9999999999832953, 0.9999999999843139, 0.9999999999852993, 0.9999999999861725,
            0.9999999999870456, 0.9999999999878133, 0.9999999999885409, 0.9999999999892686, 0.9999999999898902,
            0.9999999999904723, 0.9999999999910544, 0.9999999999916365, 0.9999999999920739, 0.9999999999925104,
            0.999999999992947, 0.9999999999933835, 0.9999999999938161, 0.999999999994107, 0.9999999999943981,
            0.9999999999946891, 0.9999999999949801, 0.9999999999952712, 0.9999999999955622, 0.9999999999958533,
            0.9999999999960634, 0.9999999999962089, 0.9999999999963545, 0.9999999999965, 0.9999999999966455, 0.999999999996791,
            0.9999999999969366, 0.999999999997082, 0.9999999999972276, 0.9999999999973731, 0.9999999999975187,
            0.9999999999976641, 0.9999999999978096, 0.9999999999979552, 0.9999999999981006, 0.9999999999982462,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816, 0.9999999999982816,
            0.9999999999982816, 1.0000000000000000 };

}
