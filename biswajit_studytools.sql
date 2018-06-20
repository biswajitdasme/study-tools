-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 17, 2018 at 08:18 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `biswajit_studytools`
--

-- --------------------------------------------------------

--
-- Table structure for table `studytools_answers`
--

CREATE TABLE `studytools_answers` (
  `id` int(11) NOT NULL,
  `examid` int(11) NOT NULL,
  `questionnum` int(11) NOT NULL,
  `answer` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `studytools_examresult`
--

CREATE TABLE `studytools_examresult` (
  `id` int(10) NOT NULL,
  `userid` int(10) NOT NULL,
  `examid` int(10) NOT NULL,
  `result` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `studytools_exams`
--

CREATE TABLE `studytools_exams` (
  `id` int(10) NOT NULL,
  `examtitle` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `studytools_questions`
--

CREATE TABLE `studytools_questions` (
  `id` int(10) NOT NULL,
  `examid` int(10) NOT NULL,
  `question` varchar(50) NOT NULL,
  `option1` varchar(50) NOT NULL,
  `option2` varchar(50) NOT NULL,
  `option3` varchar(50) NOT NULL,
  `answer` int(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `studytools_uploads`
--

CREATE TABLE `studytools_uploads` (
  `id` int(10) NOT NULL,
  `uploader` int(10) NOT NULL,
  `filename` varchar(50) NOT NULL,
  `filedescription` varchar(50) NOT NULL,
  `filetype` int(11) NOT NULL,
  `questions` int(10) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `studytools_users`
--

CREATE TABLE `studytools_users` (
  `id` int(10) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `mobile` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `type` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `studytools_users`
--

INSERT INTO `studytools_users` (`id`, `name`, `email`, `username`, `password`, `mobile`, `type`) VALUES
(11, 'Student', 'student@gmail.com', 'student', 'student', '01818000000', 1),
(12, 'Teacher', 'teacher@gmail.com', 'teacher', 'teacher', '01818000000', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `studytools_answers`
--
ALTER TABLE `studytools_answers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `studytools_examresult`
--
ALTER TABLE `studytools_examresult`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `studytools_exams`
--
ALTER TABLE `studytools_exams`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `studytools_questions`
--
ALTER TABLE `studytools_questions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `studytools_uploads`
--
ALTER TABLE `studytools_uploads`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `studytools_users`
--
ALTER TABLE `studytools_users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `studytools_answers`
--
ALTER TABLE `studytools_answers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `studytools_examresult`
--
ALTER TABLE `studytools_examresult`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `studytools_exams`
--
ALTER TABLE `studytools_exams`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `studytools_questions`
--
ALTER TABLE `studytools_questions`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `studytools_uploads`
--
ALTER TABLE `studytools_uploads`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `studytools_users`
--
ALTER TABLE `studytools_users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
