SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `PRTS` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
SHOW WARNINGS;
USE `PRTS`;

-- -----------------------------------------------------
-- Table `PRTS`.`tblLogin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PRTS`.`tblLogin` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `PRTS`.`tblLogin` (
  `EmpId` INT NOT NULL AUTO_INCREMENT ,
  `Username` VARCHAR(45) NULL ,
  `EmpPassword` VARCHAR(45) NULL ,
  `EmpStatus` VARCHAR(45) NULL ,
  PRIMARY KEY (`EmpId`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `PRTS`.`tblEmpPersonal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PRTS`.`tblEmpPersonal` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `PRTS`.`tblEmpPersonal` (
  `EmpId` INT NOT NULL ,
  `EmpFName` VARCHAR(45) NULL ,
  `EmpMName` VARCHAR(45) NULL ,
  `EmpLName` VARCHAR(45) NULL ,
  `EmpFFName` VARCHAR(45) NULL ,
  `EmpFMName` VARCHAR(45) NULL ,
  `EmpFLName` VARCHAR(45) NULL ,
  `EmpAddress` VARCHAR(100) NULL ,
  `EmpMobile` VARCHAR(45) NULL ,
  `EmpEmail` VARCHAR(45) NULL ,
  `EmpDOB` DATE NULL ,
  `EmpPAN` VARCHAR(45) NULL ,
  PRIMARY KEY (`EmpId`) ,
  CONSTRAINT `fk_tblEmpPersonal_tblLogin`
    FOREIGN KEY (`EmpId` )
    REFERENCES `PRTS`.`tblLogin` (`EmpId` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX fk_tblEmpPersonal_tblLogin ON `PRTS`.`tblEmpPersonal` (`EmpId` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `PRTS`.`tblcompany`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PRTS`.`tblcompany` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `PRTS`.`tblcompany` (
  `CompanyId` INT NOT NULL AUTO_INCREMENT ,
  `CompanyName` VARCHAR(45) NULL ,
  `Address` VARCHAR(100) NULL ,
  `City` VARCHAR(45) NULL ,
  `State` VARCHAR(45) NULL ,
  `Country` VARCHAR(45) NULL ,
  `Pin` VARCHAR(45) NULL ,
  `Phone` VARCHAR(45) NULL ,
  `Email` VARCHAR(45) NULL ,
  PRIMARY KEY (`CompanyId`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `PRTS`.`tblCompanyRequirements`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PRTS`.`tblCompanyRequirements` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `PRTS`.`tblCompanyRequirements` (
  `CompanyId` INT NOT NULL ,
  `Experience` INT NULL ,
  `SalaryOffered` DOUBLE NULL ,
  `Qualification` VARCHAR(45) NULL ,
  PRIMARY KEY (`CompanyId`) ,
  CONSTRAINT `fk_tblCompanyRequirements_tblcompany`
    FOREIGN KEY (`CompanyId` )
    REFERENCES `PRTS`.`tblcompany` (`CompanyId` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX fk_tblCompanyRequirements_tblcompany ON `PRTS`.`tblCompanyRequirements` (`CompanyId` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `PRTS`.`tblRequirementSkillSet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PRTS`.`tblRequirementSkillSet` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `PRTS`.`tblRequirementSkillSet` (
  `CompanyId` INT NOT NULL ,
  `OperatingSystem` VARCHAR(45) NULL ,
  `Technologies` VARCHAR(45) NULL ,
  `ScriptingLanguages` VARCHAR(45) NULL ,
  `DB` VARCHAR(45) NULL ,
  `Frameworks` VARCHAR(45) NULL ,
  `TestingTools` VARCHAR(45) NULL ,
  `OtherSkills` VARCHAR(45) NULL ,
  CONSTRAINT `fk_tblRequirementSkillSet_tblcompany`
    FOREIGN KEY (`CompanyId` )
    REFERENCES `PRTS`.`tblcompany` (`CompanyId` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX fk_tblRequirementSkillSet_tblcompany ON `PRTS`.`tblRequirementSkillSet` (`CompanyId` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `PRTS`.`tblPersonal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PRTS`.`tblPersonal` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `PRTS`.`tblPersonal` (
  `ProfId` INT NOT NULL AUTO_INCREMENT ,
  `ProfFName` VARCHAR(45) NULL ,
  `ProfMName` VARCHAR(45) NULL ,
  `ProfLName` VARCHAR(45) NULL ,
  `ProfFFName` VARCHAR(45) NULL ,
  `ProfFMName` VARCHAR(45) NULL ,
  `ProfFLName` VARCHAR(45) NULL ,
  `ProfAddress` VARCHAR(100) NULL ,
  `ProfMobile` VARCHAR(45) NULL ,
  `ProfEmail` VARCHAR(45) NULL ,
  `ProfDOB` DATE NULL ,
  `ProfPAN` VARCHAR(45) NULL ,
  `ProfExpectedSalary` DOUBLE NULL ,
  PRIMARY KEY (`ProfId`) )
ENGINE = InnoDB
COMMENT = 'for professional ';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `PRTS`.`tblProfQualification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PRTS`.`tblProfQualification` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `PRTS`.`tblProfQualification` (
  `QualificationId` INT NOT NULL AUTO_INCREMENT ,
  `ProfId` INT NOT NULL ,
  `QualificationName` VARCHAR(45) NULL ,
  `Branch` VARCHAR(45) NULL ,
  `YearOfPassing` VARCHAR(45) NULL ,
  `UniversityBoard` VARCHAR(45) NULL ,
  `CollegeInstitute` VARCHAR(45) NULL ,
  `PerMarks` DOUBLE NULL ,
  PRIMARY KEY (`QualificationId`) ,
  CONSTRAINT `fk_tblProfQualification_tblPersonal`
    FOREIGN KEY (`ProfId` )
    REFERENCES `PRTS`.`tblPersonal` (`ProfId` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX fk_tblProfQualification_tblPersonal ON `PRTS`.`tblProfQualification` (`ProfId` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `PRTS`.`tblProfSkillSet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PRTS`.`tblProfSkillSet` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `PRTS`.`tblProfSkillSet` (
  `ProfId` INT NOT NULL ,
  `OperatingSystem` VARCHAR(45) NULL ,
  `Technologies` VARCHAR(45) NULL ,
  `ScriptingLanguages` VARCHAR(45) NULL ,
  `DB` VARCHAR(45) NULL ,
  `Frameworks` VARCHAR(45) NULL ,
  `TestingTools` VARCHAR(45) NULL ,
  `OtherSkills` VARCHAR(45) NULL ,
  PRIMARY KEY (`ProfId`) ,
  CONSTRAINT `fk_tblProfSkillSet_tblPersonal`
    FOREIGN KEY (`ProfId` )
    REFERENCES `PRTS`.`tblPersonal` (`ProfId` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX fk_tblProfSkillSet_tblPersonal ON `PRTS`.`tblProfSkillSet` (`ProfId` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `PRTS`.`tblExperience`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PRTS`.`tblExperience` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `PRTS`.`tblExperience` (
  `ExperienceId` INT NOT NULL AUTO_INCREMENT ,
  `ProfId` INT NULL ,
  `StartDate` DATE NULL ,
  `EndDate` DATE NULL ,
  `CompanyName` VARCHAR(45) NULL ,
  `Location` VARCHAR(45) NULL ,
  `Designation` VARCHAR(45) NULL ,
  `JobProfile` VARCHAR(45) NULL ,
  `SalaryPackage` DOUBLE NULL ,
  PRIMARY KEY (`ExperienceId`) ,
  CONSTRAINT `fk_tblExperience_tblPersonal`
    FOREIGN KEY (`ProfId` )
    REFERENCES `PRTS`.`tblPersonal` (`ProfId` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX fk_tblExperience_tblPersonal ON `PRTS`.`tblExperience` (`ProfId` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `PRTS`.`tblProjectDetails`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PRTS`.`tblProjectDetails` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `PRTS`.`tblProjectDetails` (
  `ProjectId` INT NOT NULL AUTO_INCREMENT ,
  `ProfId` INT NOT NULL ,
  `ProjectName` VARCHAR(45) NULL ,
  `ProjectDescription` VARCHAR(800) NULL ,
  `ProjectStartDate` DATE NULL ,
  `ProjectEndDate` DATE NULL ,
  `TechnologiesLanguages` VARCHAR(100) NULL ,
  `ExperienceId` INT NULL ,
  PRIMARY KEY (`ProjectId`) ,
  CONSTRAINT `fk_tblProjectDetails_tblPersonal`
    FOREIGN KEY (`ProfId` )
    REFERENCES `PRTS`.`tblPersonal` (`ProfId` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tblProjectDetails_tblExperience`
    FOREIGN KEY (`ExperienceId` )
    REFERENCES `PRTS`.`tblExperience` (`ExperienceId` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX fk_tblProjectDetails_tblPersonal ON `PRTS`.`tblProjectDetails` (`ProfId` ASC) ;

SHOW WARNINGS;
CREATE INDEX fk_tblProjectDetails_tblExperience ON `PRTS`.`tblProjectDetails` (`ExperienceId` ASC) ;

SHOW WARNINGS;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
