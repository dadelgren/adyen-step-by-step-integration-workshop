// ...

@Configuration
public class DependencyInjectionConfiguration {
    private final ApplicationConfiguration applicationConfiguration;

    public DependencyInjectionConfiguration(ApplicationConfiguration applicationConfiguration) {
        this.applicationConfiguration = applicationConfiguration;
    }

    @Bean
    Client client() {
        // Step 4
        var config = new Config();
        config.setApiKey(applicationConfiguration.getAdyenApiKey()); // We now use the Adyen API Key
        config.setEnvironment(Environment.TEST);		     // Sets the environment to TEST
        return new Client(config);
    }

    @Bean
    PaymentsApi paymentsApi(){
        return new PaymentsApi(client());
    }

    @Bean
    HMACValidator hmacValidator() { return new HMACValidator(); }
}
