SELECT Invoice.Total AS bigInvoice,
       Customer.FirstName AS customerFirst,
       Customer.LastName AS customerLast,
       Employee.FirstName AS repFirst,
       Employee.LastName AS repLast,
       Employee.Email AS repEmail,
       Boss.FirstName AS bossFirst,
       Boss.LastName AS bossLast,
       Boss.Email AS bossEmail
FROM Invoice
INNER JOIN Customer ON Invoice.CustomerId = Customer.CustomerId
INNER JOIN Employee ON Customer.SupportRepId = Employee.EmployeeId
LEFT JOIN Employee AS Boss ON Employee.ReportsTo = Boss.EmployeeId
WHERE Invoice.Total > 20
  AND Invoice.BillingCountry = 'USA'
