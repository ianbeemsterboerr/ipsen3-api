package io.dropwizard;

public class View {

    public static class OnlyAdmins extends OnlyCustomers {}

    public static class OnlyCustomers extends Public {}

    public static class Public {}

}
