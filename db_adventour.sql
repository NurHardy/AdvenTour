-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 07 Mar 2016 pada 05.29
-- Versi Server: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_adventour`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `challenge`
--

CREATE TABLE IF NOT EXISTS `challenge` (
`idChallenge` int(11) NOT NULL,
  `idVenue` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` varchar(500) NOT NULL,
  `completionType` enum('location','photo') NOT NULL,
  `expReward` int(11) NOT NULL,
  `tokenReward` int(11) NOT NULL,
  `startTime` datetime NOT NULL,
  `endTime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `check_in`
--

CREATE TABLE IF NOT EXISTS `check_in` (
  `idPost` int(11) NOT NULL,
  `idVenue` int(11) NOT NULL,
  `time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
`idComment` int(11) NOT NULL,
  `idPost` int(11) NOT NULL,
  `idCommenter` int(11) NOT NULL,
  `comment` varchar(500) NOT NULL,
  `time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `event`
--

CREATE TABLE IF NOT EXISTS `event` (
`idEvent` int(11) NOT NULL,
  `idVenue` int(11) NOT NULL,
  `title` varchar(10) NOT NULL,
  `description` varchar(500) NOT NULL,
  `startTime` datetime NOT NULL,
  `endTime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `friend`
--

CREATE TABLE IF NOT EXISTS `friend` (
  `idUser` int(11) NOT NULL,
  `idFriend` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `level`
--

CREATE TABLE IF NOT EXISTS `level` (
  `level` int(11) NOT NULL,
  `minExp` int(11) NOT NULL,
  `levelName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `like`
--

CREATE TABLE IF NOT EXISTS `like` (
  `idPost` int(11) NOT NULL,
  `idLiker` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `idPost` int(11) NOT NULL,
  `idUser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `rate`
--

CREATE TABLE IF NOT EXISTS `rate` (
`idRate` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `idVenue` int(11) NOT NULL,
  `rateGiven` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `travel_journal`
--

CREATE TABLE IF NOT EXISTS `travel_journal` (
  `idPost` int(11) NOT NULL,
  `idVenue` int(11) NOT NULL,
  `journal` varchar(500) NOT NULL,
  `time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`idUser` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `exp` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `token` int(11) NOT NULL,
  `photo` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `user_challenge`
--

CREATE TABLE IF NOT EXISTS `user_challenge` (
  `idPost` int(11) NOT NULL,
  `idChallenge` int(11) NOT NULL,
  `latitude` decimal(10,0) NOT NULL,
  `longitude` decimal(10,0) NOT NULL,
  `photo` varchar(100) NOT NULL,
  `time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `venue`
--

CREATE TABLE IF NOT EXISTS `venue` (
`idVenue` int(11) NOT NULL,
  `venueName` varchar(100) NOT NULL,
  `description` varchar(500) NOT NULL,
  `popularity` int(11) NOT NULL,
  `type` varchar(50) NOT NULL,
  `cost` int(11) NOT NULL,
  `latitude` decimal(10,0) NOT NULL,
  `longitude` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `venue_photo`
--

CREATE TABLE IF NOT EXISTS `venue_photo` (
`idPhoto` int(11) NOT NULL,
  `idVenue` int(11) NOT NULL,
  `photo` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `challenge`
--
ALTER TABLE `challenge`
 ADD PRIMARY KEY (`idChallenge`), ADD KEY `idVenue` (`idVenue`), ADD KEY `idVenue_2` (`idVenue`);

--
-- Indexes for table `check_in`
--
ALTER TABLE `check_in`
 ADD KEY `idVenue` (`idVenue`), ADD KEY `idVenue_2` (`idVenue`), ADD KEY `idPost` (`idPost`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
 ADD PRIMARY KEY (`idComment`), ADD KEY `idCommenter` (`idCommenter`), ADD KEY `idPost` (`idPost`), ADD KEY `idPost_2` (`idPost`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
 ADD PRIMARY KEY (`idEvent`), ADD KEY `idVenue` (`idVenue`);

--
-- Indexes for table `friend`
--
ALTER TABLE `friend`
 ADD PRIMARY KEY (`idUser`,`idFriend`), ADD KEY `idUser` (`idUser`), ADD KEY `idFriend` (`idFriend`);

--
-- Indexes for table `level`
--
ALTER TABLE `level`
 ADD PRIMARY KEY (`level`);

--
-- Indexes for table `like`
--
ALTER TABLE `like`
 ADD KEY `idLiker` (`idLiker`), ADD KEY `idPost` (`idPost`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
 ADD PRIMARY KEY (`idPost`), ADD KEY `idUser` (`idUser`);

--
-- Indexes for table `rate`
--
ALTER TABLE `rate`
 ADD PRIMARY KEY (`idRate`), ADD KEY `idVenue` (`idVenue`), ADD KEY `idUser` (`idUser`);

--
-- Indexes for table `travel_journal`
--
ALTER TABLE `travel_journal`
 ADD KEY `idVenue` (`idVenue`), ADD KEY `idPost` (`idPost`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`idUser`), ADD KEY `level` (`level`), ADD KEY `level_2` (`level`);

--
-- Indexes for table `user_challenge`
--
ALTER TABLE `user_challenge`
 ADD KEY `idChallenge` (`idChallenge`), ADD KEY `idPost` (`idPost`);

--
-- Indexes for table `venue`
--
ALTER TABLE `venue`
 ADD PRIMARY KEY (`idVenue`);

--
-- Indexes for table `venue_photo`
--
ALTER TABLE `venue_photo`
 ADD PRIMARY KEY (`idPhoto`), ADD KEY `idVenue` (`idVenue`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `challenge`
--
ALTER TABLE `challenge`
MODIFY `idChallenge` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
MODIFY `idComment` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
MODIFY `idEvent` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `rate`
--
ALTER TABLE `rate`
MODIFY `idRate` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `venue`
--
ALTER TABLE `venue`
MODIFY `idVenue` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `venue_photo`
--
ALTER TABLE `venue_photo`
MODIFY `idPhoto` int(11) NOT NULL AUTO_INCREMENT;
--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `challenge`
--
ALTER TABLE `challenge`
ADD CONSTRAINT `challenge_ibfk_1` FOREIGN KEY (`idVenue`) REFERENCES `venue` (`idVenue`);

--
-- Ketidakleluasaan untuk tabel `check_in`
--
ALTER TABLE `check_in`
ADD CONSTRAINT `check_in_ibfk_1` FOREIGN KEY (`idPost`) REFERENCES `post` (`idPost`),
ADD CONSTRAINT `check_in_ibfk_2` FOREIGN KEY (`idVenue`) REFERENCES `venue` (`idVenue`);

--
-- Ketidakleluasaan untuk tabel `comment`
--
ALTER TABLE `comment`
ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`idCommenter`) REFERENCES `user` (`idUser`),
ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`idPost`) REFERENCES `post` (`idPost`);

--
-- Ketidakleluasaan untuk tabel `event`
--
ALTER TABLE `event`
ADD CONSTRAINT `event_ibfk_1` FOREIGN KEY (`idVenue`) REFERENCES `venue` (`idVenue`);

--
-- Ketidakleluasaan untuk tabel `friend`
--
ALTER TABLE `friend`
ADD CONSTRAINT `friend_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`),
ADD CONSTRAINT `friend_ibfk_2` FOREIGN KEY (`idFriend`) REFERENCES `user` (`idUser`);

--
-- Ketidakleluasaan untuk tabel `like`
--
ALTER TABLE `like`
ADD CONSTRAINT `like_ibfk_1` FOREIGN KEY (`idLiker`) REFERENCES `user` (`idUser`),
ADD CONSTRAINT `like_ibfk_2` FOREIGN KEY (`idPost`) REFERENCES `post` (`idPost`);

--
-- Ketidakleluasaan untuk tabel `post`
--
ALTER TABLE `post`
ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`);

--
-- Ketidakleluasaan untuk tabel `rate`
--
ALTER TABLE `rate`
ADD CONSTRAINT `rate_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`),
ADD CONSTRAINT `rate_ibfk_2` FOREIGN KEY (`idVenue`) REFERENCES `venue` (`idVenue`);

--
-- Ketidakleluasaan untuk tabel `travel_journal`
--
ALTER TABLE `travel_journal`
ADD CONSTRAINT `travel_journal_ibfk_1` FOREIGN KEY (`idPost`) REFERENCES `post` (`idPost`),
ADD CONSTRAINT `travel_journal_ibfk_2` FOREIGN KEY (`idVenue`) REFERENCES `venue` (`idVenue`);

--
-- Ketidakleluasaan untuk tabel `user`
--
ALTER TABLE `user`
ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`level`) REFERENCES `level` (`level`);

--
-- Ketidakleluasaan untuk tabel `user_challenge`
--
ALTER TABLE `user_challenge`
ADD CONSTRAINT `user_challenge_ibfk_1` FOREIGN KEY (`idChallenge`) REFERENCES `challenge` (`idChallenge`),
ADD CONSTRAINT `user_challenge_ibfk_2` FOREIGN KEY (`idPost`) REFERENCES `post` (`idPost`);

--
-- Ketidakleluasaan untuk tabel `venue_photo`
--
ALTER TABLE `venue_photo`
ADD CONSTRAINT `venue_photo_ibfk_1` FOREIGN KEY (`idVenue`) REFERENCES `venue` (`idVenue`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
