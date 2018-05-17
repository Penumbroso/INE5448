Feature: O sistema deve permitir o registro de contratos.
 
   Scenario: Cadastrar contrato com sucesso
    Given Dado o fornecedor com a razao social "Servicos Gerais"
    	And o CNPJ (Documento) "63768444000191"
    	And o fornecedor nao possui sancao ativa
    When O usuario informar o numero do contrato "098/2018"
    	And a data de assinatura do contrato "10/05/2018"
    	And a data de validade do contrato "10/05/2023"
    	And o valor total do contrato "1000.00"
    Then o sistema emite uma mensagem que o contrato foi cadastrado com sucesso
    
    Scenario: Cadastrar contrato com sucesso fornecedor com sancao sem impedimento
     Given Dado o fornecedor com a razao social "Casa Garcia Copa e Cozinha LTDA"
    	And o CNPJ (Documento) "40746762000156"
    	And o fornecedor possui no contrato "009/2018" a sancao "ATRASO ENTREGA"
    When O usuario informar o numero do contrato "567/2018"
    	And a data de assinatura do contrato "10/05/2018"
    	And a data de validade do contrato "10/05/2023"
    	And o valor total do contrato "2350987.33"
    Then o sistema emite uma mensagem que o contrato foi cadastrado com sucesso
    	And emite um alerta que o fornecedor possui uma sancao que nao gera impedimento de contratacao
    	
    Scenario: Cadastrar contrato com sucesso fornecedor com sancao com impedimento
      Given Dado o fornecedor com a razao social "Papelaria Brasileira"
    	And o CNPJ (Documento) "58025324000164"
    	And o fornecedor possui no contrato "001/2017" a sancao "INEXECUCAO CONTRATUAL"
   When O usuario informar o numero do contrato "567/2018"
    	And a data de assinatura do contrato "10/05/2018"
    	And a data de validade do contrato "10/05/2023"
    	And o valor total do contrato "2350987.33"
    Then o sistema emite uma mensagem que o contrato nao foi cadastrado
    	And emite um alerta que o fornecedor possui uma sancao que gera impedimento de contratacao
    	
    	