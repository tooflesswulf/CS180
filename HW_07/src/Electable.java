public interface Electable {
    String getName();
    boolean isCitizen();
    int getAge();
    String getPartyMembership();
    CampaignIssue getCampaignFocus();
    boolean canBeElected();
}
