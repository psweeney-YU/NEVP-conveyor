/*
Navicat MySQL Data Transfer

Source Server         : herbarium
Source Server Version : 50611
Source Host           : localhost:3306
Source Database       : herbarium

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2016-02-01 08:04:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `AuthorityId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `AuthorityName` varchar(40) NOT NULL,
  `AuthorityTabClass` varchar(255) NOT NULL,
  PRIMARY KEY (`AuthorityId`) USING BTREE,
  UNIQUE KEY `AuthorityId` (`AuthorityId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=16384;

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `CollectionId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `CollectionCode` varchar(255) DEFAULT NULL,
  `CollectionInfo` varchar(255) DEFAULT NULL,
  `InstituteId` int(11) unsigned DEFAULT NULL,
  `BCICollectionId` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`CollectionId`),
  UNIQUE KEY `CollectionId` (`CollectionId`),
  KEY `InstituteId` (`InstituteId`),
  CONSTRAINT `collection_fk` FOREIGN KEY (`InstituteId`) REFERENCES `institute` (`InstituteId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for collector
-- ----------------------------
DROP TABLE IF EXISTS `collector`;
CREATE TABLE `collector` (
  `CollectorId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `CollectorNo` varchar(20) DEFAULT NULL,
  `CollectorInfoImage` int(11) unsigned DEFAULT NULL,
  `Institute` int(11) DEFAULT NULL,
  `GUID` varchar(512) DEFAULT NULL,
  `CollectorFullName` varchar(512) DEFAULT NULL,
  `CollectorInfo` varchar(512) DEFAULT NULL,
  `Birth` varchar(40) DEFAULT NULL,
  `Death` varchar(40) DEFAULT NULL,
  `IsGroup` int(4) DEFAULT NULL,
  `Deleted` int(11) DEFAULT NULL,
  `Exported` int(11) DEFAULT NULL,
  PRIMARY KEY (`CollectorId`),
  UNIQUE KEY `CollectorId` (`CollectorId`) USING BTREE,
  KEY `CollectorInfoImage` (`CollectorInfoImage`) USING BTREE,
  CONSTRAINT `collector_ibfk_1` FOREIGN KEY (`CollectorInfoImage`) REFERENCES `image_data` (`ImageDataId`)
) ENGINE=InnoDB AUTO_INCREMENT=253161 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=3276;

-- ----------------------------
-- Table structure for folder
-- ----------------------------
DROP TABLE IF EXISTS `folder`;
CREATE TABLE `folder` (
  `FolderId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `FolderName` varchar(40) DEFAULT NULL,
  `FolderPath` varchar(1024) DEFAULT NULL,
  `FolderInfo` varchar(255) DEFAULT NULL,
  `ParentId` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`FolderId`) USING BTREE,
  UNIQUE KEY `FolderId` (`FolderId`) USING BTREE,
  KEY `ParentId` (`ParentId`) USING BTREE,
  CONSTRAINT `folder_fk1` FOREIGN KEY (`ParentId`) REFERENCES `folder` (`FolderId`)
) ENGINE=InnoDB AUTO_INCREMENT=22236 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=16384;

-- ----------------------------
-- Table structure for image_data
-- ----------------------------
DROP TABLE IF EXISTS `image_data`;
CREATE TABLE `image_data` (
  `ImageDataId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `ImageDataPath` varchar(511) NOT NULL,
  `ImageDataType` int(11) NOT NULL,
  PRIMARY KEY (`ImageDataId`) USING BTREE,
  UNIQUE KEY `ImageDataId` (`ImageDataId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for image_raw
-- ----------------------------
DROP TABLE IF EXISTS `image_raw`;
CREATE TABLE `image_raw` (
  `ImageRawId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `ImageRawPath` varchar(511) NOT NULL,
  `ImageRawName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ImageRawId`) USING BTREE,
  UNIQUE KEY `ImageRawId` (`ImageRawId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=209007 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=5461;

-- ----------------------------
-- Table structure for institute
-- ----------------------------
DROP TABLE IF EXISTS `institute`;
CREATE TABLE `institute` (
  `InstituteId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `InstituteName` varchar(100) NOT NULL,
  `InstituteInfo` varchar(255) DEFAULT NULL,
  `Deleted` int(11) DEFAULT NULL,
  PRIMARY KEY (`InstituteId`) USING BTREE,
  UNIQUE KEY `InstituteId` (`InstituteId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=8192;

-- ----------------------------
-- Table structure for mymfavorites
-- ----------------------------
DROP TABLE IF EXISTS `mymfavorites`;
CREATE TABLE `mymfavorites` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fav_name` text,
  `fav_source` text,
  `fav_path` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Please do not modify this table!';

-- ----------------------------
-- Table structure for ne_gazatteer
-- ----------------------------
DROP TABLE IF EXISTS `ne_gazatteer`;
CREATE TABLE `ne_gazatteer` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `townGNIS` varchar(24) NOT NULL,
  `townID` varchar(48) NOT NULL,
  `townName` varchar(255) NOT NULL,
  `county` varchar(48) NOT NULL,
  `state` varchar(48) NOT NULL,
  `latitudeDD` varchar(48) NOT NULL,
  `longitudeDD` varchar(48) NOT NULL,
  `uncertaintyMeters` varchar(24) NOT NULL,
  `method` varchar(48) NOT NULL,
  `georeferencingRemarks` varchar(512) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1998 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ownership
-- ----------------------------
DROP TABLE IF EXISTS `ownership`;
CREATE TABLE `ownership` (
  `OwnershipId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `OwnerId` int(11) unsigned NOT NULL,
  `rights` varchar(255) DEFAULT NULL,
  `usage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`OwnershipId`) USING BTREE,
  UNIQUE KEY `OwnershipId` (`OwnershipId`) USING BTREE,
  KEY `OwnerId` (`OwnerId`) USING BTREE,
  CONSTRAINT `ownership_fk2` FOREIGN KEY (`OwnerId`) REFERENCES `institute` (`InstituteId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=16384;

-- ----------------------------
-- Table structure for specimen
-- ----------------------------
DROP TABLE IF EXISTS `specimen`;
CREATE TABLE `specimen` (
  `SpecimenId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `Barcode` varchar(32) DEFAULT NULL,
  `CollectionCode` int(11) unsigned DEFAULT NULL,
  `SpecimenType` int(4) DEFAULT NULL,
  `ScientificName` varchar(255) DEFAULT NULL,
  `Family` varchar(255) DEFAULT NULL,
  `Genus` varchar(255) DEFAULT NULL,
  `InfraspecificRank` varchar(32) DEFAULT NULL,
  `InfraspecificEpithet` varchar(255) DEFAULT NULL,
  `SpecificEpithet` varchar(255) DEFAULT NULL,
  `RecordDate` datetime DEFAULT NULL,
  `RecordUser` int(11) unsigned DEFAULT NULL,
  `RecordEnterBy` varchar(255) DEFAULT NULL,
  `ScientificNameAuthorship` varchar(255) DEFAULT NULL,
  `Country` varchar(255) DEFAULT NULL,
  `DarwinCountry` varchar(255) DEFAULT NULL,
  `County` varchar(255) DEFAULT NULL,
  `StateProvince` varchar(255) DEFAULT NULL,
  `Town` varchar(255) DEFAULT NULL,
  `VerbatimEventDate` varchar(255) DEFAULT NULL,
  `RawImage` int(11) unsigned DEFAULT NULL,
  `ParentId` int(11) unsigned DEFAULT NULL,
  `BeginEventDate` datetime DEFAULT NULL,
  `EndEventDate` datetime DEFAULT NULL,
  `Watermark` varchar(255) DEFAULT NULL,
  `WatermarkDate` varchar(255) DEFAULT NULL,
  `SheetNotes` varchar(4096) DEFAULT NULL,
  `GeneralNotes` varchar(4096) DEFAULT NULL,
  `CollectorNotes` varchar(4096) DEFAULT NULL,
  `WrittenNumber` int(11) DEFAULT NULL,
  `FolderNumber` int(11) DEFAULT NULL,
  `Herbarium` varchar(40) DEFAULT NULL,
  `SpecimenInfoImage` int(11) unsigned DEFAULT NULL,
  `IdentificationQualifier` varchar(255) DEFAULT NULL,
  `RecordNumber` varchar(45) DEFAULT NULL,
  `CreationDate` datetime DEFAULT NULL,
  `ModificationDate` datetime DEFAULT NULL,
  `MissingInfo` int(11) DEFAULT NULL,
  `ImageError` int(11) DEFAULT NULL,
  `ExportDate` datetime DEFAULT NULL,
  `Checksum` varchar(36) DEFAULT NULL,
  `System` int(11) unsigned DEFAULT NULL,
  `StorageLocation` varchar(255) DEFAULT NULL,
  `RecordedByGUID` varchar(512) DEFAULT NULL,
  `iDropSync` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`SpecimenId`) USING BTREE,
  UNIQUE KEY `SpecimenId` (`SpecimenId`) USING BTREE,
  KEY `RawImage` (`RawImage`) USING BTREE,
  KEY `folder` (`ParentId`) USING BTREE,
  KEY `SpecimenInfoImage` (`SpecimenInfoImage`) USING BTREE,
  KEY `RecordUser` (`RecordUser`) USING BTREE,
  KEY `system` (`System`) USING BTREE,
  KEY `CollectionCode` (`CollectionCode`),
  CONSTRAINT `specimen_fk` FOREIGN KEY (`CollectionCode`) REFERENCES `collection` (`CollectionId`),
  CONSTRAINT `specimen_fk1` FOREIGN KEY (`RecordUser`) REFERENCES `user` (`UserId`),
  CONSTRAINT `specimen_fk2` FOREIGN KEY (`System`) REFERENCES `system` (`SystemId`),
  CONSTRAINT `specimen_fk3` FOREIGN KEY (`RawImage`) REFERENCES `image_raw` (`ImageRawId`),
  CONSTRAINT `specimen_fk4` FOREIGN KEY (`ParentId`) REFERENCES `folder` (`FolderId`),
  CONSTRAINT `specimen_fk5` FOREIGN KEY (`SpecimenInfoImage`) REFERENCES `image_data` (`ImageDataId`)
) ENGINE=InnoDB AUTO_INCREMENT=208572 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=5461;

-- ----------------------------
-- Table structure for spec_collector_map
-- ----------------------------
DROP TABLE IF EXISTS `spec_collector_map`;
CREATE TABLE `spec_collector_map` (
  `SpecCollectorMapId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `SpecimenId` int(11) unsigned zerofill NOT NULL,
  `CollectorId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`SpecCollectorMapId`) USING BTREE,
  UNIQUE KEY `SpecCollectorMapId` (`SpecCollectorMapId`) USING BTREE,
  KEY `SpecimenId` (`SpecimenId`) USING BTREE,
  KEY `CollectorId` (`CollectorId`) USING BTREE,
  CONSTRAINT `spec_collector_map_fk1` FOREIGN KEY (`SpecimenId`) REFERENCES `specimen` (`SpecimenId`),
  CONSTRAINT `spec_collector_map_fk2` FOREIGN KEY (`CollectorId`) REFERENCES `collector` (`CollectorId`)
) ENGINE=InnoDB AUTO_INCREMENT=206050 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=5461;

-- ----------------------------
-- Table structure for system
-- ----------------------------
DROP TABLE IF EXISTS `system`;
CREATE TABLE `system` (
  `SystemId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `SystemDescription` varchar(255) DEFAULT NULL,
  `Owner` int(11) unsigned NOT NULL,
  `Deleted` int(11) DEFAULT NULL,
  PRIMARY KEY (`SystemId`) USING BTREE,
  UNIQUE KEY `SystemId` (`SystemId`) USING BTREE,
  KEY `Owner` (`Owner`) USING BTREE,
  CONSTRAINT `system_fk1` FOREIGN KEY (`Owner`) REFERENCES `institute` (`InstituteId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=16384;

-- ----------------------------
-- Table structure for system_setting
-- ----------------------------
DROP TABLE IF EXISTS `system_setting`;
CREATE TABLE `system_setting` (
  `SettingId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `SystemId` int(11) unsigned NOT NULL,
  `SettingDate` datetime NOT NULL,
  `CameraName` varchar(255) DEFAULT NULL,
  `CameraExpoTime` varchar(20) DEFAULT NULL,
  `CameraAperture` int(11) DEFAULT NULL,
  `CameraISO` int(11) DEFAULT NULL,
  `ColorTargetPositionX` int(11) DEFAULT NULL,
  `ColorTargetPositionY` int(11) DEFAULT NULL,
  `WorkspacePath` varchar(255) DEFAULT NULL,
  `InstallationURL` varchar(255) DEFAULT NULL,
  `ConveyorVoltage` int(11) DEFAULT NULL,
  `SensorVoltage` int(11) DEFAULT NULL,
  `Deleted` int(11) DEFAULT NULL,
  PRIMARY KEY (`SettingId`) USING BTREE,
  UNIQUE KEY `SettingId` (`SettingId`) USING BTREE,
  KEY `SystemId` (`SystemId`) USING BTREE,
  CONSTRAINT `system_setting_fk1` FOREIGN KEY (`SystemId`) REFERENCES `system` (`SystemId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=16384;

-- ----------------------------
-- Table structure for taxa_bonap
-- ----------------------------
DROP TABLE IF EXISTS `taxa_bonap`;
CREATE TABLE `taxa_bonap` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Family` varchar(255) NOT NULL,
  `Genus` varchar(255) NOT NULL,
  `SpecificEpithet` varchar(255) NOT NULL,
  `SubspecificEpithet` varchar(255) NOT NULL,
  `InfraspecificEpithet` varchar(255) NOT NULL,
  `InfraspecificRank` varchar(255) NOT NULL,
  `Authorship` varchar(255) NOT NULL,
  `BONAP_ID` varchar(45) NOT NULL,
  `UUID` varchar(37) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34781 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=173;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `UserId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `Username` varchar(40) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `UUID` varchar(37) DEFAULT NULL,
  `WorksplaceURL` varchar(512) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `IPlantUserName` varchar(20) DEFAULT NULL,
  `IPlantPassword` varchar(255) DEFAULT NULL,
  `iPlantAddress` varchar(255) DEFAULT NULL,
  `iPlantZone` varchar(255) DEFAULT NULL,
  `Deleted` int(11) DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  `LastLoginTime` datetime DEFAULT NULL,
  PRIMARY KEY (`UserId`) USING BTREE,
  UNIQUE KEY `UserId` (`UserId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=16384;

-- ----------------------------
-- Table structure for user_authority_map
-- ----------------------------
DROP TABLE IF EXISTS `user_authority_map`;
CREATE TABLE `user_authority_map` (
  `UserAuthorityMapId` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `UserId` int(11) unsigned NOT NULL,
  `AuthorityId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`UserAuthorityMapId`) USING BTREE,
  UNIQUE KEY `UserAuthorityMapId` (`UserAuthorityMapId`) USING BTREE,
  KEY `UserId` (`UserId`) USING BTREE,
  KEY `AuthorityId` (`AuthorityId`) USING BTREE,
  CONSTRAINT `user_authority_map_fk1` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserId`),
  CONSTRAINT `user_authority_map_fk2` FOREIGN KEY (`AuthorityId`) REFERENCES `authority` (`AuthorityId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=16384;

-- ----------------------------
-- View structure for not_exported
-- ----------------------------
DROP VIEW IF EXISTS `not_exported`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY INVOKER  VIEW `not_exported` AS SELECT
	*
FROM
	specimen
WHERE
	ExportDate IS NULL
AND MissingInfo = 0
GROUP BY
	Barcode
ORDER BY
	CreationDate DESC ;

-- ----------------------------
-- View structure for summary
-- ----------------------------
DROP VIEW IF EXISTS `summary`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `summary` AS SELECT
	a.SpecimenId,
	#internal DB id for specimen record
	a.Barcode,
	#barcode attached to specimen, dwc:catalogNumber for NEVP
	a.CollectionCode AS 'CollCodeID',
	#id of collection code
	d.CollectionCode,
	#dwc:collectionCode for collection housing specimen
	a.ScientificName,
	#dwc:scientificName
	a.Family,
	#dwc:family
	a.Genus,
	#dwc:genus
	a.SpecificEpithet,
	#dwc:specificEpithet
	a.InfraspecificRank,
	#infraspecificRank
	a.InfraspecificEpithet,
	#dwc:infraspecificEpithet
	a.ScientificNameAuthorship,
	#dwc:scientificNameAuthorship
	a.IdentificationQualifier,
	#dwc:identificationQualifier
	g.CollectorFullName,
	#recordedBy
	a.RecordNumber AS Collection_Number,
	#dwc:recordNumber
	a.VerbatimEventDate AS Verbatim_Collection_Date,
	#dwc:verbatimEventDate
	a.BeginEventDate AS Begin_Collection_Date,
	#beginning collection date
	a.EndEventDate AS End_Collection_Date,
	#ending collection date
	a.Country,
	#dwc:country
	a.County,
	#dwc:county
	a.StateProvince,
	#dwc:stateProvince
	a.Town,
	#dwc:municipality
	a.CreationDate,
	#date record created, applies to specimen & image metadata
	a.ModificationDate,
	#dcterms:modified, applies to specimen & image metadata
	a.ExportDate,
	#date specimen record serialized in RDF/XML format
	a.Checksum,
	#md5 hash checksum of image file
	a.StorageLocation,
	#place in herbarium where specimen stored, from pre-capture app
	b.ImageRawPath,
	#path to image file on apparatus computer
	b.ImageRawName,
	#file name of image 
	c.Username,
	#username of user capturing specimen data and imaging specimen
	c.Email AS User_email,
	#email of user
	c.WorksplaceURL AS User_workplace_URL,
	#workplace url of user
	c.UUID,
	#UUID of user
	e.InstituteName,
	#name of institution, dwc:institutionCode
	f.rights,
	#dcterms:rights, used in image RDF/XML document
	f.usage,
	#xmpRights:UsageTerms, used in image RDF/XML document
	a.MissingInfo AS flagged #f.webStatement			#xmpRights:WebStatement, used in image RDF/XML document
FROM
	specimen a,
	image_raw b,
	`user` c,
	collection d,
	institute e,
	ownership f,
	collector g,
	spec_collector_map h
WHERE
	b.ImageRawId = a.RawImage
AND c.UserId = a.RecordUser
AND #(a.ExportDate="0000-00-00 00:00:00" OR a.ExportDate IS NULL) AND 
d.CollectionId = a.CollectionCode
AND e.InstituteId = d.InstituteId
AND f.OwnerId = e.InstituteId
AND h.SpecimenId = a.SpecimenId
AND g.CollectorId= h.CollectorID
GROUP BY
	a.SpecimenId
ORDER BY
	a.SpecimenId ASC ;
