DROP PROCEDURE IF EXISTS `db_scheme_changes`;
DELIMITER ;;
CREATE PROCEDURE `db_scheme_changes`() BEGIN
	DECLARE TableSchema VARCHAR(64);
	SET @TableSchema  = (SELECT DATABASE() FROM DUAL);
	
	
	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'career' AND column_name = 'USER_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE career ADD COLUMN USER_ID INT(11) NOT NULL DEFAULT 0 AFTER `CREATED_DATE`;
	END IF;
	
	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'company' AND column_name = 'USER_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE company ADD COLUMN USER_ID INT(11) NOT NULL DEFAULT 0 AFTER `CREATED_DATE`;
	END IF;
	
	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'resume' AND column_name = 'USER_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE resume ADD COLUMN USER_ID INT(11) NOT NULL DEFAULT 0 AFTER `CREATED_DATE`;
	END IF;
	
END;;

DELIMITER ;
CALL db_scheme_changes();
DROP PROCEDURE IF EXISTS `db_scheme_changes`;
