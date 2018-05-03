#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Cadastrar Produto
  Cadastrar produtos

  @CadastrarProdutos
  Scenario Outline: Cadastrar Produtos
    Given O nome do produto e <nome>
    And descricao e <descricao>
    And lance minimo e <lanceMinimo>
    And cpf do leiloador e <cpfLeiloador>
    And data limite e <dataLimite>
    And um usuario ja foi cadastrado
    And produto com mesmo <nome> nao existe
    When o produto for cadastrado
    Then o sistema deve cadastrar o produto com sucesso


    Examples: 
      | nome  	 	 | descricao 					| lanceMinimo  | 		cpfLeiloador 							| dataLimite 				 	 |
      | "biscoito" |    "delicioso" 		| 100 		 		 |		"840.287.935-72"					|			2020-01-01			 |
      | "biscoito" |    "magico" 				| 20 		 		 	 |		"840.287.935-72"					|			2021-01-01			 |
      | "bolacha"  |    "legal" 				| 20 		 		 	 |		"055.761.919-00"					|			2021-01-01			 |
