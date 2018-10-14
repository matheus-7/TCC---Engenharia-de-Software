
DROP PROCEDURE  GerarConquistas
DELIMITER $$
CREATE PROCEDURE GerarConquistas (UsuIdParam INT)
 
BEGIN
		
	DECLARE TotalAcertosMatematica INT DEFAULT 0;
    DECLARE TotalAcertosNatureza INT DEFAULT 0;
    DECLARE TotalAcertosPortugues INT DEFAULT 0;
    DECLARE TotalAcertosHumanas INT DEFAULT 0;
    DECLARE TotalAcertos INT DEFAULT 0;
    DECLARE TotalRespostas INT DEFAULT 0;

    select (COUNT(ResId))
	from resposta
	   inner join alternativa
		  on resposta.AltId = alternativa.AltId
	   inner join questao
		  on questao.QuesId = alternativa.QuesId
	where AreaId = 4
	   and AltCorreta = 1
	   and UsuId = UsuIdParam
	group by UsuId INTO TotalAcertosMatematica;
    
    select (COUNT(ResId))
	from resposta
	   inner join alternativa
		  on resposta.AltId = alternativa.AltId
	   inner join questao
		  on questao.QuesId = alternativa.QuesId
	where AreaId = 1
	   and AltCorreta = 1
	   and UsuId = UsuIdParam
	group by UsuId INTO TotalAcertosNatureza;
    
    select (COUNT(ResId))
	from resposta
	   inner join alternativa
		  on resposta.AltId = alternativa.AltId
	   inner join questao
		  on questao.QuesId = alternativa.QuesId
	where AreaId = 3
	   and AltCorreta = 1
	   and UsuId = UsuIdParam
	group by UsuId INTO TotalAcertosPortugues;
    
    select (COUNT(ResId))
	from resposta
	   inner join alternativa
		  on resposta.AltId = alternativa.AltId
	   inner join questao
		  on questao.QuesId = alternativa.QuesId
	where AreaId = 2
	   and AltCorreta = 1
	   and UsuId = UsuIdParam
	group by UsuId INTO TotalAcertosHumanas;
    
    select (COUNT(ResId))
	from resposta
	   inner join alternativa
		  on resposta.AltId = alternativa.AltId
	   inner join questao
		  on questao.QuesId = alternativa.QuesId
	where AltCorreta = 1
	   and UsuId = UsuIdParam
	group by UsuId INTO TotalAcertos;
    
    select (COUNT(ResId))
	from resposta
	where UsuId = UsuIdParam
	group by UsuId INTO TotalRespostas;
    
	if TotalAcertosMatematica >= 50 and
       (select IFNULL(COUNT(ConId), 0)
		from conquista 
        where ConConfId = 1
           and UsuId = UsuIdParam) = 0 then
       
       insert into conquista (ConConfId, UsuId,      ConData)
					values   (1,         UsuIdParam, NOW()  );
       
	END IF;
    
    if TotalAcertosNatureza >= 50 and
       (select IFNULL(COUNT(ConId), 0)
		from conquista 
        where ConConfId = 2
           and UsuId = UsuIdParam) = 0 then
       
       insert into conquista (ConConfId, UsuId,      ConData)
					values   (2,         UsuIdParam, NOW()  );
       
	END IF;
    
    if TotalAcertosPortugues >= 50 and
       (select IFNULL(COUNT(ConId), 0)
		from conquista 
        where ConConfId = 3
           and UsuId = UsuIdParam) = 0 then
       
       insert into conquista (ConConfId, UsuId,      ConData)
					values   (3,         UsuIdParam, NOW()  );
       
	END IF;
    
    if TotalAcertosHumanas >= 50 and
       (select IFNULL(COUNT(ConId), 0)
		from conquista 
        where ConConfId = 4
           and UsuId = UsuIdParam) = 0 then
       
       insert into conquista (ConConfId, UsuId,      ConData)
					values   (4,         UsuIdParam, NOW()  );
       
	END IF;
    
    if TotalRespostas >= 100 and
       (select IFNULL(COUNT(ConId), 0)
		from conquista 
        where ConConfId = 5
           and UsuId = UsuIdParam) = 0 then
       
       insert into conquista (ConConfId, UsuId,      ConData)
					values   (5,         UsuIdParam, NOW()  );
       
	END IF;
    
    if TotalRespostas >= 300 and
       (select IFNULL(COUNT(ConId), 0)
		from conquista 
        where ConConfId = 6
           and UsuId = UsuIdParam) = 0 then
       
       insert into conquista (ConConfId, UsuId,      ConData)
					values   (6,         UsuIdParam, NOW()  );
       
	END IF;
    
    if TotalRespostas >= 500 and
       (select IFNULL(COUNT(ConId), 0)
		from conquista 
        where ConConfId = 7
           and UsuId = UsuIdParam) = 0 then
       
       insert into conquista (ConConfId, UsuId,      ConData)
					values   (7,         UsuIdParam, NOW()  );
       
	END IF;
    
    if TotalAcertos >= 100 and
       (select IFNULL(COUNT(ConId), 0)
		from conquista 
        where ConConfId = 8
           and UsuId = UsuIdParam) = 0 then
       
       insert into conquista (ConConfId, UsuId,      ConData)
					values   (8,         UsuIdParam, NOW()  );
       
	END IF;
    
    if TotalAcertos >= 300 and
       (select IFNULL(COUNT(ConId), 0)
		from conquista 
        where ConConfId = 9
           and UsuId = UsuIdParam) = 0 then
       
       insert into conquista (ConConfId, UsuId,      ConData)
					values   (9,         UsuIdParam, NOW()  );
       
	END IF;
    
    if TotalAcertos >= 500 and
       (select IFNULL(COUNT(ConId), 0)
		from conquista 
        where ConConfId = 10
           and UsuId = UsuIdParam) = 0 then
       
       insert into conquista (ConConfId, UsuId,      ConData)
					values   (10,         UsuIdParam, NOW()  );
       
	END IF;
 
END$$

