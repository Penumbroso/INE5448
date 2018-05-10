@tag
Feature: Suspencao de Sancao
  Suspencao de Sancoes

  @SuspenderSancao
  Scenario Outline: Suspender de Sancao
    Given o sistema possui uma lista de fornecedores com o <fornecedor>
    And este fornecedor possui um <contrato> ativo
    And este <contrato> possui uma <sancao> sobre ele
    When o usuario seleciona a <sancao>
    And informa a <dataDeRevogacao>
    And a <defesa>
    Then o sistema emite um alerta que a sancao foi suspensa com sucesso

    Examples: 
      | fornecedor    | contrato   | sancao  | dataDeRevogacao  |  defesa									|
      | "Casa Garcia" | "033/2018" | 59      | 2018-01-01				|  "Greve dos Correios"		|
      | "MX" 					| "054/2018" | 53      | 2018-01-01				|  "Apocalipse"						|
      | "Zap Sistemas"| "033/2018" | 66      | 2018-01-01				|  "Zumbis"						    |
