-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 19. Jan 2018 um 20:52
-- Server-Version: 10.1.10-MariaDB
-- PHP-Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `cuftd`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `angreifer`
--

CREATE TABLE `angreifer` (
  `angreiferid` int(11) NOT NULL,
  `angreifertyp` varchar(20) DEFAULT NULL,
  `lebenspunkte` int(11) DEFAULT NULL,
  `lebenspunkteproattacke` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `gebaeude`
--

CREATE TABLE `gebaeude` (
  `gebaeudeid` int(11) NOT NULL,
  `level` int(11) DEFAULT NULL,
  `gebaeudetyp` varchar(20) DEFAULT NULL,
  `lebenspunkte` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `gebaeude`
--

INSERT INTO `gebaeude` (`gebaeudeid`, `level`, `gebaeudetyp`, `lebenspunkte`) VALUES
(1, 1, 'HausEinheiten1', 100),
(2, 2, 'HausEinheiten2', 200),
(3, 3, 'HausEinheiten3', 500),
(4, 1, 'GebEnergie1', 50),
(5, 2, 'GebEnergie2', 100),
(6, 3, 'GebEnergie3', 150),
(7, 1, 'ZaunEnergie1', 10),
(8, 2, 'ZaunEnergie2', 20),
(9, 3, 'ZaunEnergie3', 50);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `overworld`
--

CREATE TABLE `overworld` (
  `owid` int(11) NOT NULL,
  `anzahlSpieler` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `overworld`
--

INSERT INTO `overworld` (`owid`, `anzahlSpieler`) VALUES
(1, 9);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `overworldfield`
--

CREATE TABLE `overworldfield` (
  `owfid` int(11) NOT NULL,
  `xKoordinaten` int(11) DEFAULT NULL,
  `yKoordinaten` int(11) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `owid` int(11) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `overworldfield`
--

INSERT INTO `overworldfield` (`owfid`, `xKoordinaten`, `yKoordinaten`, `username`, `owid`) VALUES
(14, 1, 0, 'Sara', 1),
(15, 2, 0, 'Verena', 1),
(16, 3, 0, 'Tom', 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `spieler`
--

CREATE TABLE `spieler` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `erfahrungspunkte` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `geldeinheiten` int(11) DEFAULT NULL,
  `uwid` int(100) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `datum` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `spieler`
--

INSERT INTO `spieler` (`username`, `password`, `erfahrungspunkte`, `level`, `geldeinheiten`, `uwid`, `timestamp`, `datum`) VALUES
('', NULL, 25, 7, 600, NULL, '2018-01-19 07:34:56', '0000-00-00'),
('Sara', '101', 68, 7, 600, 16, '2018-01-19 07:42:21', '2018-01-19'),
('Tom', '101', 76, 4, 800, 18, '2018-01-19 00:26:42', '0000-00-00'),
('Verena', '101', 68, 7, 600, 17, '2018-01-19 07:40:59', '2018-01-19');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `underworld`
--

CREATE TABLE `underworld` (
  `uwid` int(11) NOT NULL,
  `gebeaudeanzahl` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `underworld`
--

INSERT INTO `underworld` (`uwid`, `gebeaudeanzahl`) VALUES
(16, 0),
(17, 0),
(18, 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `underworldfield`
--

CREATE TABLE `underworldfield` (
  `udfid` int(11) NOT NULL,
  `xKoordinaten` int(11) DEFAULT NULL,
  `yKoordinaten` int(11) DEFAULT NULL,
  `gebaeudeid` int(11) DEFAULT NULL,
  `uwid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `underworldfield`
--

INSERT INTO `underworldfield` (`udfid`, `xKoordinaten`, `yKoordinaten`, `gebaeudeid`, `uwid`) VALUES
(279, 3, 1, 7, 18),
(280, 4, 1, 8, 18),
(281, 5, 1, 8, 18),
(282, 6, 1, 8, 18),
(283, 7, 1, 7, 18),
(284, 3, 2, 7, 18),
(285, 4, 2, 5, 18),
(286, 5, 2, 5, 18),
(287, 6, 2, 5, 18),
(288, 7, 2, 7, 18),
(289, 3, 3, 7, 18),
(290, 4, 3, 2, 18),
(291, 5, 3, 2, 18),
(292, 6, 3, 2, 18),
(293, 7, 3, 7, 18),
(294, 3, 4, 7, 18),
(295, 4, 4, 1, 18),
(296, 5, 4, 4, 18),
(297, 6, 4, 1, 18),
(298, 7, 4, 7, 18),
(299, 3, 5, 7, 18),
(300, 4, 5, 8, 18),
(301, 5, 5, 8, 18),
(302, 6, 5, 8, 18),
(303, 7, 5, 7, 18),
(328, 2, 1, 9, 17),
(329, 3, 1, 9, 17),
(330, 4, 1, 9, 17),
(331, 5, 1, 9, 17),
(332, 6, 1, 9, 17),
(333, 7, 1, 9, 17),
(334, 2, 2, 9, 17),
(335, 3, 2, 5, 17),
(336, 4, 2, 2, 17),
(337, 5, 2, 1, 17),
(338, 6, 2, 4, 17),
(339, 7, 2, 9, 17),
(340, 2, 3, 9, 17),
(341, 3, 3, 3, 17),
(342, 4, 3, 6, 17),
(343, 5, 3, 6, 17),
(344, 6, 3, 3, 17),
(345, 7, 3, 9, 17),
(346, 2, 4, 9, 17),
(347, 3, 4, 4, 17),
(348, 4, 4, 1, 17),
(349, 5, 4, 2, 17),
(350, 6, 4, 5, 17),
(351, 7, 4, 9, 17),
(352, 2, 5, 9, 17),
(353, 3, 5, 9, 17),
(354, 4, 5, 9, 17),
(355, 5, 5, 9, 17),
(356, 6, 5, 9, 17),
(357, 7, 5, 9, 17),
(386, 3, 1, 7, 16),
(387, 4, 1, 8, 16),
(388, 5, 1, 8, 16),
(389, 6, 1, 7, 16),
(390, 3, 2, 7, 16),
(391, 4, 2, 4, 16),
(392, 5, 2, 1, 16),
(393, 6, 2, 7, 16),
(394, 3, 3, 7, 16),
(395, 4, 3, 1, 16),
(396, 5, 3, 4, 16),
(397, 6, 3, 7, 16),
(398, 3, 4, 7, 16),
(399, 4, 4, 8, 16),
(400, 5, 4, 8, 16),
(401, 6, 4, 7, 16);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `angreifer`
--
ALTER TABLE `angreifer`
  ADD PRIMARY KEY (`angreiferid`);

--
-- Indizes für die Tabelle `gebaeude`
--
ALTER TABLE `gebaeude`
  ADD PRIMARY KEY (`gebaeudeid`);

--
-- Indizes für die Tabelle `overworld`
--
ALTER TABLE `overworld`
  ADD PRIMARY KEY (`owid`);

--
-- Indizes für die Tabelle `overworldfield`
--
ALTER TABLE `overworldfield`
  ADD PRIMARY KEY (`owfid`),
  ADD KEY `username` (`username`),
  ADD KEY `owid` (`owid`);

--
-- Indizes für die Tabelle `spieler`
--
ALTER TABLE `spieler`
  ADD PRIMARY KEY (`username`),
  ADD KEY `uwid` (`uwid`);

--
-- Indizes für die Tabelle `underworld`
--
ALTER TABLE `underworld`
  ADD PRIMARY KEY (`uwid`);

--
-- Indizes für die Tabelle `underworldfield`
--
ALTER TABLE `underworldfield`
  ADD PRIMARY KEY (`udfid`),
  ADD KEY `uwid` (`uwid`),
  ADD KEY `gebaeudeid` (`gebaeudeid`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `angreifer`
--
ALTER TABLE `angreifer`
  MODIFY `angreiferid` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `gebaeude`
--
ALTER TABLE `gebaeude`
  MODIFY `gebaeudeid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT für Tabelle `overworldfield`
--
ALTER TABLE `overworldfield`
  MODIFY `owfid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT für Tabelle `underworld`
--
ALTER TABLE `underworld`
  MODIFY `uwid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT für Tabelle `underworldfield`
--
ALTER TABLE `underworldfield`
  MODIFY `udfid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=402;
--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `overworldfield`
--
ALTER TABLE `overworldfield`
  ADD CONSTRAINT `overworldfield_ibfk_1` FOREIGN KEY (`username`) REFERENCES `spieler` (`username`);

--
-- Constraints der Tabelle `spieler`
--
ALTER TABLE `spieler`
  ADD CONSTRAINT `spieler_ibfk_1` FOREIGN KEY (`uwid`) REFERENCES `underworld` (`uwid`);

--
-- Constraints der Tabelle `underworldfield`
--
ALTER TABLE `underworldfield`
  ADD CONSTRAINT `underworldfield_ibfk_1` FOREIGN KEY (`uwid`) REFERENCES `underworld` (`uwid`),
  ADD CONSTRAINT `underworldfield_ibfk_2` FOREIGN KEY (`gebaeudeid`) REFERENCES `gebaeude` (`gebaeudeid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
