    @SuppressWarnings("unchecked")
    private Map<String, Object> safeMap(Object value) {
        if (value instanceof Map) {
            return (Map<String, Object>) value;
        }
        return null;
    }

    private Amount amountFromMap(Map<String, Object> amountMap, Long fallbackValue) {
        if (amountMap == null) {
            return null;
        }
        var currency = amountMap.getOrDefault("currency", "EUR").toString();
        var valueObj = amountMap.get("value");
        Long value = null;
        if (valueObj instanceof Number number) {
            value = number.longValue();
        } else if (valueObj instanceof String stringValue && !stringValue.isBlank()) {
            try {
                value = Long.parseLong(stringValue);
            } catch (NumberFormatException e) {
                log.warn("Unable to parse amount value {}", stringValue);
                return null;
            }
        }

        if (value == null) {
            value = fallbackValue;
        }

        if (value == null) {
            log.warn("Amount value missing for request");
            return null;
        }

        var amount = new Amount();
        amount.setCurrency(currency);
        amount.setValue(value);
        return amount;
    }