
insert into usuario (UsuNome, UsuEmail, UsuSenha, UsuDireito, UsuAtivo, UsuDataCad)
             values ('Matheus Socoloski Velho', 'maatheeus10@hotmail.com', 'ÏÃÖÊÇ×Õ', 'Administrador', 1, NOW());

insert into area_conhecimento (AreaNome)
                       values ('Ciências da Natureza e suas Tecnologias'),
							  ('Ciências Humanas e suas Tecnologias'), 
                              ('Linguagens, Códigos e suas Tecnologias'), 
                              ('Matemática e suas Tecnologias');
							  
insert into questao (AreaId, QuesDesc, QuesAtiva, QuesDataCad)
			values	(1,      'Um geneticista observou que determinada plantação era sensível a um tipo de praga que atacava as flores da lavoura. Ao mesmo tempo, ele percebeu que uma erva daninha que crescia associada às plantas não era destruída. A partir de técnicas de manipulação genética, em laboratório, o gene da resistência à praga foi inserido nas plantas cultivadas, resolvendo o problema.
							  \n Do ponto de vista da biotecnologia, como essa planta resultante da intervenção é classificada?',       1,         NOW()),
					(1,      'A célula fotovoltaica é uma aplicação prática do efeito fotoelétrico. Quando a luz incide sobre certas substâncias, libera elétrons que, circulando livremente de átomo para átomo, formam uma corrente elétrica. Uma célula fotovoltaica é composta por uma placa de ferro recoberta por uma camada de selênio e uma película transparente de ouro. A luz atravessa a película, incide sobre o selênio e retira elétrons, que são atraídos pelo ouro, um ótimo condutor de eletricidade. A película de ouro é conectada à placa de ferro, que recebe os elétrons e os devolve para o selênio, fechando o circuito e formando uma corrente elétrica de pequena intensidade.
							  \n DIAS, C. B. Célula fotovoltaica. Disponível em: http://super.abril.com.br. Acesso em: 16 ago. 2012 (adaptado).
							  \n O processo biológico que se assemelha ao descrito é a',       1,         NOW()),
                    (1,      'Os distúrbios por deficiência de iodo (DDI) são fenômenos naturais e permanentes amplamente distribuídos em várias regiões do mundo. Populações que vivem em áreas deficientes em iodo têm o risco de apresentar os distúrbios causados por essa deficiência, cujos impactos sobre os níveis de desenvolvimento humano, social e econômico são muito graves. No Brasil, vigora uma lei que obriga os produtores de sal de cozinha a incluírem em seu produto certa quantidade de iodeto de potássio.
							  \n Essa inclusão visa prevenir problemas em qual glândula humana?',       1,         NOW()),
                    (1,      'O Conselho Nacional do Meio Ambiente (Conama) estabelece os limites máximos de chumbo, cádmio e mercúrio para as familiares pilhas e baterias portáteis comercializadas no território nacional e os critérios e padrões para o seu gerenciamento ambientalmente adequado. Os estabelecimentos que comercializam esses produtos, bem como a rede de assistência técnica autorizada, devem receber dos usuários as pilhas e baterias usadas para repasse aos respectivos fabricantes ou importadores.
							  \n Resolução Conama n. 401, de 4 de novembro de 2008. Disponível em: www.mma.gov.br. Acesso em: 14 maio 2013 (adaptado).
							  \n Do ponto de vista ambiental, a destinação final apropriada para esses produtos é',       1,         NOW()),
                    (1,      'No Brasil, os postos de combustíveis comercializavam uma gasolina com cerca de 22% de álcool anidro. Na queima de 1 litro desse combustível são liberados cerca de 2 kg de CO2 na atmosfera. O plantio de árvores pode atenuar os efeitos dessa emissão de CO2. A quantidade de carbono fixada por uma árvore corresponde a aproximadamente 50% de sua biomassa seca, e para cada 12 g de carbono fixados, 44 g de CO2 são retirados da atmosfera. No Brasil, o plantio de eucalipto (Eucalyptus grandis) é bem difundido, sendo que após 11 anos essa árvore pode ter a massa de 106 kg, dos quais 29 kg são água.
							  \n Uma única árvore de Eucalyptus grandis, com as características descritas, é capaz de fixar a quantidade de CO2 liberada na queima de um volume dessa gasolina mais próximo de',       1,         NOW()),
                    (1,      'A horticultura tem sido recomendada para a agricultura familiar, porém as perdas são grandes devido à escassez de processos compatíveis para conservar frutas e hortaliças. O processo, denominado desidratação osmótica, tem se mostrado uma alternativa importante nesse sentido, pois origina produtos com boas condições de armazenamento e qualidade semelhante à matéria-prima.
							  \n GOMES, A. T.; CEREDA, M. P.; VILPOUX, O. Desidratação osmótica: uma tecnologia de baixo custo para o desenvolvimento da agricultura familiar. Revista Brasileira de Gestão e Desenvolvimento Regional, n. 3, set.-dez. 2007 (adaptado).
							  \n Esse processo para conservar os alimentos remove a água por',       1,         NOW()),
                    (1,      'Uma grande virada na moderna história da agricultura ocorreu depois da Segunda Guerra Mundial. Após a guerra, os governos haviam se deparado com um enorme excedente de nitrato de amônio, ingrediente usado na fabricação de explosivos. A partir daí as fábricas de munição foram adaptadas para começar a produzir fertilizantes tendo como componente principal os nitratos.
							  \n SOUZA, F. A. Agricultura natural/orgânica como instrumento de fixação biológica e manutenção do nitrogênio no solo: um modelo sustentável de MDL. Disponível em: www.planetaorganico.com.br. Acesso em: 17 jul. 2015 (adaptado).
							  \n No ciclo natural do nitrogênio, o equivalente ao principal componente desses fertilizantes industriais é produzido na etapa de',       1,         NOW()),
                    (1,      'Um fato corriqueiro ao se cozinhar arroz é o derramamento de parte da água de cozimento sobre a chama azul do fogo, mudando-a para uma chama amarela. Essa mudança de cor pode suscitar interpretações diversas, relacionadas às substâncias presentes na água de cozimento. Além do sal de cozinha (NaCl), nela se encontram carboidratos, proteínas e sais minerais.
							  \n Cientificamente, sabe-se que essa mudança de cor da chama ocorre pela',       1,         NOW()),
                    (1,      'A técnica do carbono-14 permite a datação de fósseis pela medição dos valores de emissão beta desse isótopo presente no fóssil. Para um ser em vida, o máximo são 15 emissões beta/(min g). Após a morte, a quantidade de 14C se reduz pela metade a cada 5 730 anos.
							  \n A prova do carbono 14. Disponível em: http://noticias.terra.com.br. Acesso em: 9 nov. 2013 (adaptado).
							  \n onsidere que um fragmento fóssil de massa igual a 30 g foi encontrado em um sítio arqueológico, e a medição de radiação apresentou 6 750 emissões beta por hora. A idade desse fóssil, em anos, é ',       1,         NOW()),
                    (1,      'As centrífugas são equipamentos utilizados em laboratórios, clínicas e indústrias. Seu funcionamento faz uso da aceleração centrífuga obtida pela rotação de um recipiente e que serve para a separação de sólidos em suspensão em líquidos ou de líquidos misturados entre si.
							  \n RODITI, I. Dicionário Houaiss de física. Rio de Janeiro: Objetiva, 2005 (adaptado).
							  \n Nesse aparelho, a separação das substâncias ocorre em função ',       1,         NOW()),
                    (2,      'Os direitos civis, surgidos na luta contra o Absolutismo real, ao se inscreverem nas primeiras constituições modernas, aparecem como se fossem conquistas definitivas de toda a humanidade. Por isso, ainda hoje invocamos esses velhos “direitos naturais” nas batalhas contra os regimes autoritários que subsistem.
							  \n QUIRINO, C. G.; MONTES, M. L. Constituições. São Paulo: Ática, 1992 (adaptado).
							  \n O conjunto de direitos ao qual o texto se refere inclui',       1,         NOW()),
                    (2,      'As primeiras ações acerca do patrimônio histórico no Brasil datam da década de 1930, com a criação do Serviço do Patrimônio Histórico e Artístico Nacional (SPHAN), em 1937. Nesse período, o conceito que norteou a política de patrimônio limitou-se aos monumentos arquitetônicos relacionados ao passado brasileiro e vinculava-se aos ideais modernistas de conhecer, compreender e recriar o Brasil por meio da valorização da tradição.
							  \n SANTOS, G. Poder e patrimônio histórico: possibilidades de diálogo entre educação histórica e educação patrimonial no ensino médio. EntreVer, n. 2, jan.-jun. 2012.
							  \n Considerando o contexto mencionado, a criação dessa política patrimonial objetivou a',       1,         NOW()),
                    (2,      'Em 1914, o preço da borracha despencou no mercado internacional; dois anos depois, 200 firmas foram à falência em Manaus. E assim acabou o sonho de quem acendia charutos com notas de 1 000 réis. A cidade entrou em colapso.
							  \n National Geographic, n. 143, fev. 2012 (adaptado).
							  \n O súbito declínio da atividade econômica mencionada foi provocado pelo(a)',       1,         NOW()),
                    (2,      'O povo que exerce o poder não é sempre o mesmo povo sobre quem o poder é exercido, e o falado self-government [autogoverno] não é o governo de cada qual por si mesmo, mas o de cada qual por todo o resto. Ademais, a vontade do povo significa praticamente a vontade da mais numerosa e ativa parte do povo — a maioria, ou aqueles que logram êxito em se fazerem aceitar como a maioria.
							  \n MILL, J. S. Sobre a liberdade. Petrópolis: Vozes, 1991 (adaptado).
							  \n No que tange à participação popular no governo, a origem da preocupação enunciada no texto encontra-se na ',       1,         NOW()),
                    (2,      'O movimento abolicionista, que levou à libertação dos escravos pela Lei Áurea em 13 de maio de 1888, foi a primeira campanha de dimensões nacionais com participação popular. Nunca antes tantos brasileiros se haviam mobilizado de forma tão intensa por uma causa comum, nem mesmo durante a Guerra do Paraguai. Envolvendo todas as regiões e classes sociais, carregou multidões a comícios e manifestações públicas e mudou de forma dramática as relações políticas e sociais que até então vigoravam no país.
							  \n GOMES, L. 1889. São Paulo: Globo, 2013 (adaptado).
							  \n O movimento social citado teve como seu principal veículo de propagação o(a)',       1,         NOW()),
                    (2,      'A experiência do movimento organizado de mulheres no Brasil oferece excelente exemplo de como se pode utilizar a lei em favor da melhoria do status jurídico, da condição social, do avanço no sentido de uma presença mais efetiva no processo de decisão política. Ao longo de quase todo o século XX, com mais intensidade em algumas décadas do que em outras, as mulheres brasileiras conseguiram obter vitórias expressivas. Algumas vezes, abolindo dispositivos legais discriminatórios, outras, conseguindo aprovar novas leis.
							  \n TABAK, F A lei como instrumento de mudança social. In: TABAK, F; VERUCCI, F. A difícil igualdade: os direitos da mulher como direitos humanos. Rio de Janeiro: Relume Dumará, 1994.
							  \n A atuação do movimento social abordado no texto resultou, na década de 1930, em',       1,         NOW()),
                    (2,      'A eugenia, tal como originalmente concebida, era a aplicação de “boas práticas de melhoramento” ao aprimoramento da espécie humana. Francis Galton foi o primeiro a sugerir com destaque o valor da reprodução humana controlada, considerando-a produtora do aperfeiçoamento da espécie.
							  \n ROSE, M. O espectro de Darwin. Rio de Janeiro: Zahar, 2000 (adaptado).
							  \n Um resultado da aplicação dessa teoria, disseminada a partir da segunda metade do século XIX, foi o(a)',       1,         NOW()),
                    (2,      'No Segundo Reinado, a Monarquia brasileira recorreu ao simbolismo de determinadas figuras e alegorias. A análise da imagem e do texto revela que o objetivo de tal estratégia era',       1,         NOW()),
                    (2,      'O número de votantes potenciais em 1872 era de 1 097 698, o que correspondia a 10,8% da população total. Esse número poderia chegar a 13%, quando separamos os escravos dos demais indivíduos. Em 1886, cinco anos depois de a Lei Saraiva ter sido aprovada, o número de cidadãos que poderiam se qualificar eleitores era de 117 022, isto é, 0,8% da população.
							  \n CASTELLUCCI, A. A. S. Trabalhadores, máquina política e eleições na Primeira República. Disponível em: www.ifch.unicamp.br. Acesso em: 28 jul. 2012.
							  \n A explicação para a alteração envolvendo o número de eleitores no período é a ',       1,         NOW()),
                    (2,      'Muito usual no Estado Novo de Vargas, a composição de Ary Barroso é um exemplo típico de',       1,         NOW()),
                    (3,      'O movimento natural do corpo segue as leis cotidianas: o menor esforço para o maior efeito. Etienne Decroux inverte a frase e cria o que, para ele, seria uma das mais importantes leis da arte: o maior esforço para o menor efeito. “Se eu pedir a um ator que me expresse alegria, ele me fará assim (fazia uma grande máscara de alegria com o rosto), mas se eu cobrir o seu rosto com um pano ou uma máscara neutra, amarrar seus braços para trás e lhe pedir que me expresse agora a alegria, ele precisará de anos de estudo”, dizia.
							  \n CAFIERO, C. Revista do Lume, n. 5, jul. 2003.
							  \n No texto, Carlota Cafiero expõe a concepção elaborada por Etienne Decroux, que desafia o ator a estabelecer uma comunicação com o público sem as expressões convencionais, por meio da',       1,         NOW()),
                    (3,      'Escrever prosa é uma arte ingrata. Eu digo prosa fiada, como faz um cronista; não a prosa de um ficcionista, na qual este é levado meio a tapas pelas personagens e situações que, azar dele, criou porque quis. Com um prosador do cotidiano, a coisa fia mais fino. Senta-se diante de sua máquina, acende um cigarro, olha através da janela e busca fundo em sua imaginação um fato qualquer, de preferência colhido no noticiário matutino, ou da véspera, em que, com as suas artimanhas peculiares, possa injetar um sangue novo.
							  \n MORAES, V. Para viver um grande amor: crônicas e poemas. São Paulo: Cia. das Letras, 1991.
							  \n Nesse trecho, Vinicius de Moraes exercita a crônica para pensá-la como gênero e prática. Do ponto de vista dele, cabe ao cronista',       1,         NOW()),
                    (3,      'PROPAGANDA — O exame dos textos e mensagens de Propaganda revela que ela apresenta posições parciais, que refletem apenas o pensamento de uma minoria, como se exprimissem, em vez disso, a convicção de uma população; trata-se, no fundo, de convencer o ouvinte ou o leitor de que, em termos de opinião, está fora do caminho certo, e de induzi-lo a aderir às teses que lhes são apresentadas, por um mecanismo bem conhecido da psicologia social, o do conformismo induzido por pressões do grupo sobre o indivíduo isolado.
							  \n BOBBIO, N.; MATTEUCCI, N.; PASQUINO, G. Dicionário de política. Brasília: UnB, 1998 (adaptado).
							  \n De acordo com o texto, as estratégias argumentativas e o uso da linguagem na produção da propaganda favorecem a ',       1,         NOW()),
                    (3,      'Naquele tempo eu morava no Calango-Frito e não acreditava em feiticeiros.
							  \n E o contrassenso mais avultava, porque, já então, - e excluída quanta coisa-e-sousa de nós todos lá, e outras cismas corriqueiras tais: sal derramado; padre viajando com a gente no trem; não falar em raio: quando muito, e se o tempo está bom, “faísca”; nem dizer lepra; só o “mal”; passo de entrada com o pé esquerdo; ave do pescoço pelado; risada renga de suindara; cachorro, bode e galo, pretos; [...] - porque, já então, como ia dizendo, eu poderia confessar, num recenseio aproximado: doze tabus de não uso próprio; oito regrinhas ortodoxas preventivas; vinte péssimos presságios; dezesseis casos de batida obrigatória na madeira; dez outros exigindo a figa digital napolitana, mas da legítima, ocultando bem a cabeça do polegar; e cinco ou seis indicações de ritual mais complicado; total: setenta e dois - noves fora, nada.
							  \n ROSA, J. G. São Marcos. Sagarana. Rio de Janeiro: José Olympio, 1967 (adaptado).
							  \n João Guimarães Rosa, nesse fragmento de conto, resgata a cultura popular ao registrar ',       1,         NOW()),
                    (3,      'As lutas podem ser classificadas de diferentes formas, de acordo com a relação espacial entre os oponentes. As lutas de contato direto são caracterizadas pela manutenção do contato direto entre os adversários, os quais procuram empurrar, desequilibrar, projetar ou imobilizar o oponente. Já as lutas que mantêm o adversário a distância são caracterizadas pela manutenção de uma distância segura em relação ao adversário, para não ser atingido pelo oponente, procurando o contato apenas no momento da aplicação de uma técnica (golpe).
							  \n Secretaria de Estado da Educação. Diretrizes curriculares de educação física para os anos finais do ensino fundamental e para o ensino médio. Curitiba: SEED, 2008 (adaptado).
							  \n Com base na classificação presente no texto, são exemplos de luta de contato direto e de luta que mantém o adversário a distância, respectivamente,',       1,         NOW()),
                    (3,      'Nessa notícia, publicada no jornal argentino Página 12, citam-se comentários de Estela Carlotto, presidente da associação Abuelas de Plaza de Mayo, com relação a uma decisão do tribunal argentino. No contexto da fala, a expressão “una de cal y otra de arena” é utilizada para ',       1,         NOW()),
                    (3,      'Desde Nápoles hasta Johannesburgo, desde Buenos Aires hasta Barcelona, los actos de xenofobia y racismo indican que nos encontramos ante un fenómeno global. Definida por la Real Academia de la Lengua como el “odio, repugnancia y hostilidad a los extranjeros”, la xenofobia va de la mano com los flujos migratorios por razones económicas o ambientales, y el desplazamiento forzado provocado por los conflictos armados internos y las guerras. El otro, el que viste, habla y tiene otra cultura y una religión diferente es visto con sospecha, desconfianza y temor en los países del llamado primer mundo. Los políticos de derecha y los grandes medios “ensalzan lo propio y denigren lo ajeno” contribuyendo a crear un clima de miedo y odio hacia el extraño y desconocido.
							  \n TAMAYO, G. E. Disponível em: www.alainet.org. Acesso em: 23 fev. 2012.
							  \n No texto, a relação entre o fenômeno discriminatório e a postura de políticos de direita e de grandes meios de comunicação tem a função de',       1,         NOW()),
                    (3,      'A Lion used to prowl about a field in which Four Oxen used to live. Many a time he tried to attack them; but whenever he came near, they turned their tails to one another, so that whichever way he approached them he was met by the horns of one of them. At last, however, they quarreled among themselves, and each went off to pasture alone in a separate corner of the field. Then the Lion attacked them one by one and soon made an end of all four.
							  \n Disponível em: www.aesopfables.com. Acesso em: 1 dez. 2011.
							  \n A fábula The Four Oxen and the Lion ilustra um preceito moral, como se espera em textos desse gênero. Essa moral, podendo ser compreendida como o tema do texto, está expressa em:',       1,         NOW()),
                    (3,      'Contemporary Argentine history is a roller coaster of financial booms and cracks, set to gripping political soap operas. But through all the highs and lows, one thing has remained constant: Buenos Aires’s graceful elegance and cosmopolitan cool. This attractive city continues to draw food lovers, design buffs and party people with its riotous night life, fashion-forward styling and a favorable exchange rate. Even with the uncertain economy, the creative energy and enterprising spirit of Porteños, as residents are called, prevail — just look to the growing ranks of art spaces, boutiques, restaurants and hotels.
							  \n SINGER, P. Disponível em: www.nytimes.com. Acesso em: 30 jul. 2012.
							  \n Nesse artigo de jornal, Buenos Aires é apresentada como a capital argentina, que',       1,         NOW()),
                    (3,      'Own a renovated house for less than $290 per month!!!!!!!! New windows, siding, flooring (laminate throughout and tile in entry way and bathroom), kitchen cabinets, counter top, back door, fresh paint and laundry on main floor. Heat billsare very low due to a good solid house and an energy efficient furnace.
							  \n Disponível em: www.freerealestaeads.net .Acesso em: 30 nov. 2011(adaptado).
							  \n Em jornais, há diversos anúncios que servem aos leitores. O conteúdo do anúncio veiculado por este texto interessará a alguém que esteja procurando',       1,         NOW()),
                    (4,      'Em alguns países anglo-saxões, a unidade de volume utilizada para indicar o conteúdo de alguns recipientes é a onça fluida britânica. O volume de uma onça fluida britânica corresponde a 28,4130625 mL.
							  A título de simplificação, considere uma onça fluida britânica correspondendo a 28 mL.
							  Nessas condições, o volume de um recipiente com capacidade de 400 onças fluidas britânicas, em cm3, é igual a',       1,         NOW()),
                    (4,      'Uma aluna estuda numa turma de 40 alunos. Em um dia, essa turma foi dividida em três salas, A, B e C, de acordo com a capacidade das salas. Na sala A Acaram 10 alunos, na B, outros 12 alunos e na C, 18 alunos. Será feito um sorteio no qual, primeiro, será sorteada uma sala e, posteriormente, será sorteado um aluno dessa sala.
							  \n Qual é a probabilidade de aquela aluna específica ser sorteada, sabendo que ela está na sala C?',       1,         NOW()),
                    (4,      'O governo decidiu reduzir de 25% para 20% o teor de álcool anidro misturado à gasolina vendida nos postos do país. Considere que a média de desempenho, ou seja, a quantidade de quilômetros (km) que um carro anda com 1 litro de combustível, é diretamente proporcional à porcentagem de gasolina presente no combustível, e que a média de desempenho de um carro antes da decisão do governo era de 13,5 km/L.
							  \n Nas condições do texto, qual será a estimativa da média de desempenho após a redução de álcool anidro no combustível?',       1,         NOW()),
                    (4,      'Numa turma de inclusão de jovens e adultos na educação formal profissional (Proeja), a média aritmética das idades dos seus dez alunos é de 32 anos. Em determinado dia, o aluno mais velho da turma faltou e, com isso, a média aritmética das idades dos nove alunos presentes foi de 30 anos.
							  \n Disponível em: http://portal.mec.gov.br. Acesso em: 10 mar. 2012 (adaptado).
							  \n 	Qual é a idade do aluno que faltou naquela turma? ',       1,         NOW()),
                    (4,      'Duas amigas irão fazer um curso no exterior durante 60 dias e usarão a mesma marca de xampu. Uma delas gasta um frasco desse xampu em 10 dias enquanto que a outra leva 20 dias para gastar um frasco com o mesmo volume. Elas combinam de usar, conjuntamente, cada frasco de xampu que levarem.
							  \n O número mínimo de frascos de xampu que deverão levar nessa viagem é',       1,         NOW()),
                    (4,      'No centro de uma praça será construída uma estátua que ocupará um terreno quadrado com área de 9 metros quadrados. O executor da obra percebeu que a escala do desenho na planta baixa do projeto é de 1 : 25.
							  \n Na planta baixa, a área da figura que representa esse terreno, em centímetro quadrado, é',       1,         NOW()),
                    (4,      'A baixa procura por carne bovina e o aumento de oferta de animais para abate fizeram com que o preço da arroba do boi apresentasse queda para o consumidor. No ano de 2012, o preço da arroba do boi caiu de R$ 100,00 para R$ 93,00.
							  \n Disponível em: www.diariodemarilia.com.br. Acesso em: 14 ago. 2012.
							  \n Com o mesmo valor destinado à aquisição de carne, em termos de perda ou ganho, o consumidor',       1,         NOW()),
                    (4,      'Uma pessoa encheu o cartão de memória de sua câmera duas vezes, somente com vídeos e fotos. Na primeira vez, conseguiu armazenar 10 minutos de vídeo e 190 fotos. Já na segunda, foi possível realizar 15 minutos de vídeo e tirar 150 fotos. Todos os vídeos possuem a mesma qualidade de imagem entre si, assim como todas as fotos. Agora, essa pessoa deseja armazenar nesse cartão de memória exclusivamente fotos, com a mesma qualidade das anteriores.
							  \n Disponível em: www.techlider.com.br. Acesso em: 31 jul. 2012.
							  \n O número máximo de fotos que ela poderá armazenar é',       1,         NOW()),
                    (4,      'As empresas que possuem Serviço de Atendimento ao Cliente (SAC), em geral, informam ao cliente que utiliza o serviço um número de protocolo de atendimento. Esse número resguarda o cliente para eventuais reclamações e é gerado, consecutivamente, de acordo com os atendimentos executados. Ao término do mês de janeiro de 2012, uma empresa registrou como último número de protocolo do SAC o 390 978 467. Do início do mês de fevereiro até o fim do mês de dezembro de 2012, foram abertos 22 580 novos números de protocolos.
							  \n O algarismo que aparece na posição da dezena de milhar do último número de protocolo de atendimento registrado em 2012 pela empresa é',       1,         NOW()),
                    (4,      'Em certa loja de roupas, o lucro na venda de uma camiseta é de 25% do preço de custo da camiseta pago pela loja. Já o lucro na venda de uma bermuda é de 30% do preço de custo da bermuda, e na venda de uma calça o lucro é de 20% sobre o preço de custo da calça. Um cliente comprou nessa loja duas camisetas, cujo preço de custo foi R$ 40,00 cada uma, uma bermuda que teve preço de custo de R$ 60,00 e duas calças, ambas com mesmo preço de custo. Sabe-se que, com essa compra, o cliente proporcionou um lucro de R$ 78,00 para a loja.
							  \n Considerando essas informações, qual foi o preço de custo, em real, pago por uma calça?',       1,         NOW());
							  
							  
insert into alternativa (QuesId, AltDesc, AltCorreta)
				 values (1,      'Clone.',      0),
						(1,      'Híbrida.',      0),
                        (1,      'Mutante.',      0),
                        (1,      'Dominante.',      0),
                        (1,      'Transgênica.',      1),
                        (2,      'fotossíntese.',      1),
                        (2,      'fermentação.',      0),
                        (2,      'quimiossíntese.',      0),
                        (2,      'hidrólise de ATP.',      0),
                        (2,      'respiração celular.',      0),
                        (3,      'Hipófise.',      0),
                        (3,      'Tireoide.',      1),
                        (3,      'Pâncreas.',      0),
                        (3,      'Suprarrenal.',      0),
                        (3,      'Paratireoide.',      0),
                        (4,      'direcionar as pilhas e baterias para compostagem.',      0),
                        (4,      'colocar as pilhas e baterias em um coletor de lixo seletivo.',      0),
                        (4,      'enviar as pilhas e baterias usadas para firmas de recarga.',      0),
                        (4,      'acumular as pilhas e baterias em armazéns de estocagem.',      0),
                        (4,      'destinar as pilhas e baterias à reutilização de seus componentes.',      1),
                        (5,      '19 L.',      0),
                        (5,      '39 L.',      0),
                        (5,      '71 L.',      1),
                        (5,      '97 L.',      0),
                        (5,      '141 L.',      0),
                        (6,      'aumento do ponto de ebulição do solvente.',      0),
                        (6,      'passagem do soluto através de uma membrana semipermeável.',      0),
                        (6,      'utilização de solutos voláteis, que facilitam a evaporação do solvente.',      0),
                        (6,      'aumento da volatilidade do solvente pela adição de solutos ao produto.',      0),
                        (6,      'pressão gerada pela diferença de concentração entre o produto e a solução.',      1),
                        (7,      'nitratação.',      1),
                        (7,      'nitrosação.',      0),
                        (7,      'amonificação.',      0),
                        (7,      'desnitrificação.',      0),
                        (7,      'fixação biológica do N2.',      0),
                        (8,      'reação do gás de cozinha com o sal, volatilizando gás cloro.',      0),
                        (8,      'emissão de fótons pelo sódio, excitado por causa da chama.',      1),
                        (8,      'produção de derivado amarelo, pela reação com o carboidrato.',      0),
                        (8,      'reação do gás de cozinha com a água, formando gás hidrogênio.',      0),
                        (8,      'excitação das moléculas de proteínas, com formação de luz amarela. ',      0),
                        (9,      '450. ',      0),
                        (9,      '1 433.',      0),
                        (9,      '11 460.',      1),
                        (9,      '17 190.',      0),
                        (9,      '27 000.',      0),
                        (10,      'das diferentes densidades.',      1),
                        (10,      'dos diferentes raios de rotação.',      0),
                        (10,      'das diferentes velocidades angulares.',      0),
                        (10,      'das diferentes quantidades de cada substância.',      0),
                        (10,      'da diferente coesão molecular de cada substância.',      0),
                        (11,      'voto secreto e candidatura em eleições.',      0),
                        (11,      'moradia digna e vagas em universidade.',      0),
                        (11,      'previdência social e saúde de qualidade.',      1),
                        (11,      'igualdade jurídica e liberdade de expressão.',      0),
                        (11,      'filiação partidária e participação em sindicatos.',      0),
                        (12,      'consolidação da historiografia oficial.',      0),
                        (12,      'definição do mercado cultural.',      0),
                        (12,      'afirmação da identidade nacional.',      1),
                        (12,      'divulgação de sítios arqueológicos.',      0),
                        (12,      'universalização de saberes museológicos. ',      0),
                        (13,      'carência de meios de transporte que permitissem uma rápida integração entre as áreas produtoras e consumidoras.',      0),
                        (13,      'produção nas plantações de seringueiras do sudeste asiático, que ocasionou um excesso da produção mundial.',      1),
                        (13,      'chamado encilhamento, que resultou na desvalorização da moeda brasileira após forte especulação na Bolsa de Valores.',      0),
                        (13,      'fim da migração de nordestinos para a Amazônia, que gerou uma enorme carência de mão de obra na região.',      0),
                        (13,      'início da Primeira Guerra Mundial, que paralisou o comércio internacional e provocou o declínio da economia brasileira. ',      0),
                        (14,      'conquista do sufrágio universal.',      0),
                        (14,      'criação do regime parlamentarista.',      0),
                        (14,      'institucionalização do voto feminino.',      0),
                        (14,      'decadência das monarquias hereditárias.',      0),
                        (14,      'consolidação da democracia representativa.',      1),
                        (15,      'imprensa escrita.',      1),
                        (15,      'oficialato militar. ',      0),
                        (15,      'corte palaciana. ',      0),
                        (15,      'clero católico. ',      0),
                        (15,      'câmara de representantes.',      0),
                        (16,      'direito de voto.',      1),
                        (16,      'garantia de cotas.',      0),
                        (16,      'acesso ao trabalho.',      0),
                        (16,      'organização partidária.',      0),
                        (16,      'igualdade de oportunidades.',      0),
                        (17,      'aprovação de medidas de inclusão social.',      0),
                        (17,      'adoção de crianças com diferentes características físicas.',      0),
                        (17,      'estabelecimento de legislação que combatia as divisões sociais.',      0),
                        (17,      'prisão e esterilização de pessoas com características consideradas inferiores.',      1),
                        (17,      'desenvolvimento de próteses que possibilitavam a reabilitação de pessoas deficientes. ',      0),
                        (18,      'exaltar o modelo absolutista e despótico.',      0),
                        (18,      'valorizar a mestiçagem africana e nativa.',      0),
                        (18,      'reduzir a participação democrática e popular.',      0),
                        (18,      'mobilizar o sentimento patriótico e antilusitano.',      0),
                        (18,      'obscurecer a origem portuguesa e colonizadora.',      1),
                        (19,      'criação da Justiça Eleitoral. ',      0),
                        (19,      'exigência da alfabetização.',      1),
                        (19,      'redução da renda nacional.',      0),
                        (19,      'exclusão do voto feminino.',      0),
                        (19,      'coibição do voto de cabresto.',      0),
                        (20,      'samba exaltação.',      1),
                        (20,      'música de sátira.',      0),
                        (20,      'hino revolucionário.',      0),
                        (20,      'propaganda eleitoral.',      0),
                        (20,      'marchinha de protesto.',      0),
                        (21,      'estética facial.',      0),
                        (21,      'mímica corporal.',      1),
                        (21,      'amarra no corpo.',      0),
                        (21,      'função da máscara. ',      0),
                        (21,      'simbologia do tecido.',      0),
                        (22,      'criar fatos com a imaginação.',      0),
                        (22,      'reproduzir as notícias dos jornais.',      0),
                        (22,      'escrever em linguagem coloquial.',      0),
                        (22,      'construir personagens verossímeis.',      0),
                        (22,      'ressignificar o cotidiano pela escrita.',      1),
                        (23,      'reflexão da sociedade sobre os produtos anunciados. ',      0),
                        (23,      'difusão do pensamento e das preferências das grandes massas. ',      0),
                        (23,      'imposição das idéias e posições de grupos específicos.',      1),
                        (23,      'decisão consciente do consumidor a respeito de sua compra.',      0),
                        (23,      'identificação dos interesses do responsável pelo produto divulgado.',      0),
                        (24,      'trechos de cantigas.',      0),
                        (24,      'rituais de mandingas.',      0),
                        (24,      'citações de preceitos.',      0),
                        (24,      'cerimônias religiosas.',      0),
                        (24,      'exemplos de superstições.',      1),
                        (25,      'judô e karatê.',      1),
                        (25,      'jiu-jítsu e sumô.',      0),
                        (25,      'boxe e kung fu.',      0),
                        (25,      'esgrima e luta olímpica.',      0),
                        (25,      'Muay Thai e tae kwon do.',      0),
                        (26,      'referir-se ao fato de a decisão judicial não implicar a sua imediata aplicação.',      0),
                        (26,      'destacar a inevitável execução da sentença.',      0),
                        (26,      'ironizar a parcialidade da Justiça nessa ação.',      1),
                        (26,      'criticar a coleta compulsória do material genético.',      0),
                        (26,      'enfatizar a determinação judicial como algo consolidado. ',      0),
                        (27,      'denunciar as práticas que encobrem as diferenças.',      0),
                        (27,      'tornar públicas as razões econômicas da xenofobia.',      0),
                        (27,      'criticar aqueles que favorecem a aparição do medo.',      1),
                        (27,      'reclamar das atitudes tomadas pelos países desenvolvidos.',      0),
                        (27,      'apontar as causas que determinam os fluxos migratórios.',      0),
                        (28,      'O mais forte sempre vence.',      0),
                        (28,      'A união faz a força.',      1),
                        (28,      'A força carrega a justiça nas costas.',      0),
                        (28,      'O ataque é a melhor defesa.',      0),
                        (28,      'O inimigo da vida é a morte.',      0),
                        (29,      'foi objeto de novelas televisivas baseadas em sua vida noturna e artística.',      0),
                        (29,      'manteve sua elegância e espírito cosmopolita, apesar das crises econômicas.',      1),
                        (29,      'teve sua energia e aspecto empreendedor ofuscados pela incerteza da economia.',      0),
                        (29,      'foi marcada historicamente por uma vida financeira estável, com repercussão na arte.',      0),
                        (29,      'parou de atrair apreciadores da gastronomia, devido ao alto valor de sua moeda.',      0),
                        (30,      'emprego no setor imobiliário.',      0),
                        (30,      'imóvel residencial para compra.',      1),
                        (30,      'serviço de reparos em domicílio.',      0),
                        (30,      'pessoa para trabalho doméstico.',      0),
                        (30,      'curso de decorador de interiores.',      0),
                        (31,      '11 200.',      1),
                        (31,      '1 120.	',      0),
                        (31,      '112.',      0),
                        (31,      '11,2.',      0),
                        (31,      '1,12.',      0),
                        (32,      '1/3',      0),
                        (32,      '1/18',      0),
                        (32,      '1/40',      0),
                        (32,      '1/54',      1),
                        (32,      '7/18',      0),
                        (33,      '10,80 km/L',      0),
                        (33,      '12,65 km/L',      0),
                        (33,      '12,82 km/L',      0),
                        (33,      '14,15 km/L ',      0),
                        (33,      '14,40 km/L',      1),
                        (34,      '18',      0),
                        (34,      '20',      0),
                        (34,      '31',      0),
                        (34,      '50',      1),
                        (34,      '62',      0),
                        (35,      '2. ',      0),
                        (35,      '4. ',      0),
                        (35,      '6. ',      0),
                        (35,      '8. ',      0),
                        (35,      '9.',      1),
                        (36,      '144.',      1),
                        (36,      '225.',      0),
                        (36,      '3 600.',      0),
                        (36,      '7 500.',      0),
                        (36,      '32 400.',      0),
                        (37,      'ganhou 6,5% em poder aquisitivo de carne. ',      0),
                        (37,      'ganhou 7% em poder aquisitivo de carne. ',      0),
                        (37,      'ganhou 7,5% em poder aquisitivo de carne. ',      1),
                        (37,      'perdeu 7% em poder aquisitivo de carne.',      0),
                        (37,      'perdeu 7,5% em poder aquisitivo de carne.',      0),
                        (38,      '200.',      0),
                        (38,      '209.',      0),
                        (38,      '270.',      1),
                        (38,      '340.',      0),
                        (38,      '475.',      0),
                        (39,      '0.',      1),
                        (39,      '2. ',      0),
                        (39,      '4.',      0),
                        (39,      '6. ',      0),
                        (39,      '8.',      0),
                        (40,      '90',      0),
                        (40,      '100',      1),
                        (40,      '125	',      0),
                        (40,      '195',      0),
                        (40,      '200',      0);
						
						
insert into conquista_config (ConConfTitulo, ConConfDesc)
                      values ('MATEMÁTICO',  'Acertou 50 questões sobre matemática e suas tecnologias.'),
							 ('MANJA DA NATUREZA', 'Acertou 50 questões sobre ciências da natureza e suas tecnologias.'),
                             ('O ESCRITOR', 'Acertou 50 questões sobre linguagens, códigos e suas tecnologias.'),
                             ('NÃO É UM ROBÔ', 'Acertou 50 questões sobre ciências humanas e suas tecnologias.'),
                             ('O INSISTENTE', 'Respondeu 100 questões.'),
                             ('VICIADO', 'Respondeu 300 questões.'),
                             ('EXTRATERRESTRE', 'Respondeu 500 questões.'),
                             ('MELHOR ALUNO', 'Acertou 100 questões.'),
                             ('O NERD', 'Acertou 300 questões.'),
                             ('EINSTEN', 'Acertou 500 questões.');
							 
insert into curso (CurNome, CurDataCad)
			values('Engenharia de Software', NOW());
			
insert into universidade(CidId, UniNome, UniDataCad)
                  values(4006,     'UFPR', NOW());
				  
				  
insert into universidade_curso (CurId, UniId)
                         values(1, 1);
                        

                    
	
                    