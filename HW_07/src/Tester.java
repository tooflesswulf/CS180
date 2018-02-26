/**
 * Tester
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 2/25/18
 *
 */

public class Tester {
    public static void main(String[] args) {
        PresidentialCandidate trump = new PresidentialCandidate("Donald Trump",
                true, 71, "Republican");
        System.out.println(trump.getName() + " of the " + trump.getPartyMembership()
                + " party " +(trump.canBeElected() ? "can run for President." :
                "cannot run for President."));
        SenatorialCandidate elizabeth = new SenatorialCandidate("Elizabeth II",
                false, 91, "Independent");
        System.out.println(elizabeth.getName() + " of the " +
                elizabeth.getPartyMembership() + " party " +
                (elizabeth.canBeElected() ? "can run for Senate." :
                        "cannot run for Senate."));
        trump.updateCampaignFocus(CampaignIssue.IMMIGRATION);
        System.out.println(trump.getName() + "'s campaign focuses on " +
                trump.getCampaignFocus() + ".");
    }
}