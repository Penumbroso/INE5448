Feature: Quando um fornecedor possui um atestado de capacidade t�cnica ativo, isto �,
que a data de validade � menor que a data atual, por�m, este atestado est� suspenso h�
mais de 90 dias, o sistema dever� cancelar este atestado automaticamente.
 
   Scenario: Cancelar um atestado de capacidade t�cnica suspenso por mais de 90 dias
    Given Dado o fornecedor com razao social "KML Refrigeracao LTDA"
    	And o numero do documento (CNPJ) "50011445000145"
    	And o fornecedor possui um atestado de capacidade tecnica ativo
    	And o atestado de capacidade tecnica esta suspenso por mais de 90 dias
    	And o atestado de capacidade tecnica nao esta cancelado
    When O usuario logar no sistema
    Then o sistema deve alterar a situacao do atestado de capacidade tecnica para cancelado
    	And alterar a data de cancelamento do atestado de capacidade tecnica para a data atual
    	And emitir um alerta de que o atestado de capacidade tecnica foi cancelado