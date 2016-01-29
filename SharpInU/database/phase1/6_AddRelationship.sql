-- Add relationship Post-Category
ALTER TABLE `post` 
ADD CONSTRAINT `Post_Category`
  FOREIGN KEY (`CATEGORY_ID`)
  REFERENCES `category` (`CATEGORY_ID`)
  ON DELETE RESTRICT
  ON UPDATE NO ACTION;
  
-- Add relationship Post-User
ALTER TABLE `post` 
ADD CONSTRAINT `Post_User`
  FOREIGN KEY (`USER_ID`)
  REFERENCES `user` (`USER_ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

-- Add relationship Career-User
ALTER TABLE `career` 
ADD CONSTRAINT `Career_User`
  FOREIGN KEY (`USER_ID`)
  REFERENCES `user` (`USER_ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

-- Add relationship Company-User
ALTER TABLE `company` 
ADD CONSTRAINT `Company_User`
  FOREIGN KEY (`USER_ID`)
  REFERENCES `user` (`USER_ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
-- Add relationship Resume-User
ALTER TABLE `resume` 
ADD CONSTRAINT `Resume_User`
  FOREIGN KEY (`USER_ID`)
  REFERENCES `user` (`USER_ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;