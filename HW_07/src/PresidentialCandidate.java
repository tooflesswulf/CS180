/**
 * PresidentialCandidate
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 2/25/18
 *
 */

public class PresidentialCandidate implements Electable{
    private String name;
    private boolean citizenship;
    private int age;
    private String party;
    private CampaignIssue campaignFocus;
    
    public PresidentialCandidate(String name, boolean citizenship, int age, String party) {
        this(name, citizenship, age, party, CampaignIssue.NO_FOCUS);
    }
    
    public PresidentialCandidate(String name, boolean citizenship, int age, String party,
                                 CampaignIssue campaignFocus) {
        this.name = name;
        this.citizenship = citizenship;
        this.age = age;
        this.party = party;
        this.campaignFocus = campaignFocus;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public boolean isCitizen() {
        return citizenship;
    }
    
    @Override
    public int getAge() {
        return age;
    }
    
    @Override
    public String getPartyMembership() {
        return party;
    }
    
    @Override
    public CampaignIssue getCampaignFocus() {
        return campaignFocus;
    }
    
    @Override
    public boolean canBeElected() {
        if(getName() == null) return false;
        if(getPartyMembership() == null) return false;
        if(!isCitizen()) return false;
        return age >= 35;
    }
    
    public void updateCampaignFocus(CampaignIssue ci) {
        campaignFocus = ci;
    }
}