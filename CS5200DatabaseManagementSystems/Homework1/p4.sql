SELECT DISTINCT CASE
                    WHEN BillingCountry = 'USA' THEN BillingState
                    ELSE BillingState || ' (' || BillingCountry || ')'
                END AS billingState
FROM Invoice
WHERE Invoice.BillingState IS NOT NULL
ORDER BY BillingState
