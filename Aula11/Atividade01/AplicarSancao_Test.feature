@tag
Feature: Aplicacao de Sancao
  Aplicacoes de Sancoes

  @AplicarSancao
  Scenario Outline: Aplicacao de Sancao
    Given exista uma lista com o <fornecedor> no sistema
    And este <fornecedor> possui um contrato ativo
    When o usuario seleciona um <contrato>
    And informa <sancao>
    And a <dataDeInicio>
    And a <dataDeFim> caso exista uma
    And a <justificativa>
    And a <quemEAplicada> a sancao
    And o usuario confirma o cadastro da sancao
    Then o sistema emite um alerta confirmando o cadastro
    And o registro da sancao e armazenado com a data atual

    Examples: 
      | fornecedor             |  contrato       | sancao      |  dataDeInicio  |  dataDeFim  |  justificativa           |  quemEAplicada   |
      | "KML Refrigeracao"     |  "142/2017"     | 59          |  2018-02-02		|	 2020-03-03 |  "Atrado de Entrega"     |  "Fornecedor"	  |
      | "ZAP Sistemas"         |  "033/2018"     | 32          |  2017-03-04    |  2023-04-05 |  "Entrega for do padrao" |  "Socio"			    |
      | "MX"                   |  "054/2018"     | 33          |  2020-03-04    |  2021-04-05 |  "Inexecucao contratual" |  "Fornecedor"	  |
      
