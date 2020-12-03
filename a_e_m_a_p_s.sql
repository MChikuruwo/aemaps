-- phpMyAdmin SQL Dump
-- version 5.0.0-alpha1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 03, 2020 at 04:45 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `a_e_m_a_p_s`
--

-- --------------------------------------------------------

--
-- Table structure for table `activity`
--

CREATE TABLE `activity` (
  `id` bigint(11) NOT NULL,
  `employee_id` bigint(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `activity_importance` bigint(11) NOT NULL,
  `activity_status` bigint(11) NOT NULL,
  `activity_requests` bigint(11) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `date_updated` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `activity_importance`
--

CREATE TABLE `activity_importance` (
  `id` bigint(11) NOT NULL,
  `importance` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `activity_importance`
--

INSERT INTO `activity_importance` (`id`, `importance`) VALUES
(1, 'LOW'),
(2, 'MEDIUM'),
(3, 'HIGH'),
(4, 'EXTREMELY_HIGH');

-- --------------------------------------------------------

--
-- Table structure for table `activity_requests`
--

CREATE TABLE `activity_requests` (
  `id` bigint(11) NOT NULL,
  `employee_id` bigint(11) NOT NULL,
  `activity` bigint(11) NOT NULL,
  `activity_request_action` bigint(11) NOT NULL,
  `activity_request_status` bigint(11) NOT NULL,
  `request_date` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `activity_request_action`
--

CREATE TABLE `activity_request_action` (
  `id` bigint(11) NOT NULL,
  `action` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `activity_request_action`
--

INSERT INTO `activity_request_action` (`id`, `action`) VALUES
(1, 'ADD'),
(2, 'COMPLETE');

-- --------------------------------------------------------

--
-- Table structure for table `activity_request_status`
--

CREATE TABLE `activity_request_status` (
  `id` bigint(11) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `activity_request_status`
--

INSERT INTO `activity_request_status` (`id`, `status`) VALUES
(1, 'PENDING'),
(2, 'APPROVED'),
(3, 'REJECTED');

-- --------------------------------------------------------

--
-- Table structure for table `activity_status`
--

CREATE TABLE `activity_status` (
  `id` bigint(11) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `activity_status`
--

INSERT INTO `activity_status` (`id`, `status`) VALUES
(1, 'PENDING'),
(2, 'ACTIVE'),
(3, 'COMPLETED');

-- --------------------------------------------------------

--
-- Table structure for table `annual_activity_calendar`
--

CREATE TABLE `annual_activity_calendar` (
  `id` bigint(11) NOT NULL,
  `activity` varchar(255) NOT NULL,
  `activity_day` date NOT NULL,
  `date_updated` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `business_unit`
--

CREATE TABLE `business_unit` (
  `id` int(11) NOT NULL,
  `business_unit` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `business_unit`
--

INSERT INTO `business_unit` (`id`, `business_unit`) VALUES
(1, 'BULAWAYO CAMPUS'),
(2, 'HARARE OFFICE');

-- --------------------------------------------------------

--
-- Table structure for table `delegation_of_duty`
--

CREATE TABLE `delegation_of_duty` (
  `id` bigint(11) NOT NULL,
  `employee_id` bigint(11) NOT NULL,
  `assign_to` bigint(11) NOT NULL,
  `duty` text NOT NULL,
  `from_date` date NOT NULL,
  `to_date` date NOT NULL,
  `reason` text DEFAULT NULL,
  `date_created` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `id` int(11) NOT NULL,
  `department` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`id`, `department`) VALUES
(1, 'COMPUTER SCIENCE'),
(2, 'ENGINEERING'),
(3, 'BUILT ENVIRONMENT');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` bigint(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `employment_status_id` bigint(20) NOT NULL,
  `job_title_id` bigint(11) NOT NULL,
  `employee_code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `gender` enum('M','F') NOT NULL,
  `mobile_number` varchar(13) DEFAULT NULL,
  `residential_status` enum('PERMANENT','MORTGAGE','RENTAL','') NOT NULL,
  `address_1` text NOT NULL,
  `monthly_salary` float DEFAULT NULL,
  `account_number` varchar(50) DEFAULT NULL,
  `date_created` datetime NOT NULL DEFAULT current_timestamp(),
  `date_updated` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `user_id`, `employment_status_id`, `job_title_id`, `employee_code`, `name`, `surname`, `gender`, `mobile_number`, `residential_status`, `address_1`, `monthly_salary`, `account_number`, `date_created`, `date_updated`) VALUES
(1, 2, 1, 1, 'N0172516T', 'Nigel', 'Chikuruwo', 'M', NULL, 'PERMANENT', '669 LANCEWOOD ROAD, VENTERSBERG, SUNWAY CITY, RUWA', 20000, '90988-7654', '2020-12-03 09:21:12', '2020-12-03 09:21:12');

-- --------------------------------------------------------

--
-- Table structure for table `employee_status`
--

CREATE TABLE `employee_status` (
  `id` bigint(11) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee_status`
--

INSERT INTO `employee_status` (`id`, `status`) VALUES
(1, 'PERMANENT'),
(2, 'CONTRACT');

-- --------------------------------------------------------

--
-- Table structure for table `job_title`
--

CREATE TABLE `job_title` (
  `id` bigint(11) NOT NULL,
  `business_unit_id` int(11) NOT NULL,
  `department_id` int(11) NOT NULL,
  `title_name` varchar(255) NOT NULL,
  `date_created` datetime NOT NULL DEFAULT current_timestamp(),
  `date_updated` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `job_title`
--

INSERT INTO `job_title` (`id`, `business_unit_id`, `department_id`, `title_name`, `date_created`, `date_updated`) VALUES
(1, 1, 1, 'LECTURER', '2020-12-03 07:09:13', '2020-12-03 07:09:13'),
(2, 1, 1, 'TUTOR', '2020-12-03 07:09:24', '2020-12-03 07:09:24'),
(3, 1, 1, 'EXAMINOR', '2020-12-03 07:09:37', '2020-12-03 07:09:37'),
(4, 1, 2, 'EXAMINOR', '2020-12-03 07:12:26', '2020-12-03 07:12:26'),
(5, 1, 2, 'LECTURER', '2020-12-03 07:12:35', '2020-12-03 07:12:35'),
(6, 1, 2, 'TUTOR', '2020-12-03 07:12:42', '2020-12-03 07:12:42'),
(7, 1, 3, 'LECTURER', '2020-12-03 07:12:53', '2020-12-03 07:12:53'),
(8, 1, 3, 'TUTOR', '2020-12-03 07:13:00', '2020-12-03 07:13:00'),
(9, 1, 3, 'EXAMINOR', '2020-12-03 07:13:07', '2020-12-03 07:13:07');

-- --------------------------------------------------------

--
-- Table structure for table `loan`
--

CREATE TABLE `loan` (
  `id` bigint(11) NOT NULL,
  `loan_application_id` bigint(11) NOT NULL,
  `employee_id` bigint(11) NOT NULL,
  `date_approved` date NOT NULL,
  `date_created` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `loan_application`
--

CREATE TABLE `loan_application` (
  `id` bigint(11) NOT NULL,
  `employee_id` bigint(11) NOT NULL,
  `loan_type_id` int(11) NOT NULL,
  `amount_required` decimal(18,2) NOT NULL,
  `repayment_period` varchar(10) NOT NULL,
  `loan_purpose` text DEFAULT NULL,
  `contract_expiry_date` date NOT NULL,
  `has_been_approved` bit(1) NOT NULL DEFAULT b'0',
  `date_created` datetime NOT NULL DEFAULT current_timestamp(),
  `date_updated` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `loan_type`
--

CREATE TABLE `loan_type` (
  `id` int(11) NOT NULL,
  `type` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loan_type`
--

INSERT INTO `loan_type` (`id`, `type`) VALUES
(1, 'CAR'),
(2, 'DEVICE'),
(3, 'MEDICAL'),
(4, 'MORTGAGE'),
(5, 'PERSONAL'),
(6, 'SCHOOL_FEES');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `user_id`, `date`) VALUES
(1, 1, '2020-12-02 12:20:29'),
(2, 1, '2020-12-02 12:52:52'),
(3, 1, '2020-12-02 14:16:31'),
(4, 1, '2020-12-02 15:57:46'),
(5, 1, '2020-12-03 06:53:18');

-- --------------------------------------------------------

--
-- Table structure for table `monthly_employee_payroll`
--

CREATE TABLE `monthly_employee_payroll` (
  `id` bigint(11) NOT NULL,
  `employee_id` bigint(11) NOT NULL,
  `job_title_id` bigint(11) NOT NULL,
  `contract_type` bigint(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `mobile_number` varchar(13) DEFAULT NULL,
  `monthly_salary` double NOT NULL,
  `date_updated` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `next_of_kin`
--

CREATE TABLE `next_of_kin` (
  `id` bigint(11) NOT NULL,
  `employee_id` bigint(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` text NOT NULL,
  `relationship_to_applicant` varchar(255) NOT NULL,
  `address` text NOT NULL,
  `mobile_number` varchar(30) NOT NULL,
  `email_address` varchar(100) DEFAULT NULL,
  `date_created` datetime NOT NULL DEFAULT current_timestamp(),
  `date_updated` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `notices`
--

CREATE TABLE `notices` (
  `id` bigint(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `notice` text NOT NULL,
  `date_created` datetime NOT NULL DEFAULT current_timestamp(),
  `date_updated` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `other_loans/overdrafts`
--

CREATE TABLE `other_loans/overdrafts` (
  `pending_loans_id` bigint(11) NOT NULL,
  `loan_bank_name` varchar(255) DEFAULT NULL,
  `loan_amount` varchar(255) DEFAULT NULL,
  `loan_balance` varchar(255) DEFAULT NULL,
  `monthly_repayment` varchar(255) DEFAULT NULL,
  `expiry_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table Nullable if no pending loans';

-- --------------------------------------------------------

--
-- Table structure for table `resignation_/_grievances`
--

CREATE TABLE `resignation_/_grievances` (
  `id` bigint(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `reason(s)` text NOT NULL,
  `has_been_approved` bit(1) NOT NULL DEFAULT b'0',
  `date_created` datetime NOT NULL DEFAULT current_timestamp(),
  `date_updated` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ADMIN'),
(2, 'USER'),
(3, 'GUEST');

-- --------------------------------------------------------

--
-- Table structure for table `salary_advance`
--

CREATE TABLE `salary_advance` (
  `id` bigint(11) NOT NULL,
  `salary_advance_application_id` bigint(11) NOT NULL,
  `employee_id` bigint(11) NOT NULL,
  `date_approved` date NOT NULL,
  `date_created` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `salary_advance_application`
--

CREATE TABLE `salary_advance_application` (
  `id` bigint(11) NOT NULL,
  `employee_id` bigint(11) NOT NULL,
  `receiving_manager` bigint(11) NOT NULL,
  `amount_required` decimal(18,2) NOT NULL,
  `has_been_approved` bit(1) NOT NULL DEFAULT b'0',
  `purpose` text NOT NULL,
  `date_created` datetime NOT NULL DEFAULT current_timestamp(),
  `date_updated` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `spouse_details`
--

CREATE TABLE `spouse_details` (
  `id` bigint(11) NOT NULL,
  `employee_id` bigint(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` text NOT NULL,
  `national_id_number` varchar(100) NOT NULL,
  `employer` varchar(255) DEFAULT NULL,
  `employer_address` text DEFAULT NULL,
  `mobile_number` varchar(30) NOT NULL,
  `email_address` varchar(100) DEFAULT NULL,
  `date_created` datetime NOT NULL DEFAULT current_timestamp(),
  `date_updated` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `staff_transfer`
--

CREATE TABLE `staff_transfer` (
  `id` bigint(11) NOT NULL,
  `releasing_manager` bigint(11) NOT NULL,
  `department_to_assign` int(11) NOT NULL,
  `transferring_employee` bigint(11) NOT NULL,
  `proposed_start_date` date NOT NULL,
  `reason` text NOT NULL,
  `has_been_approved` bit(1) NOT NULL DEFAULT b'0',
  `date_created` datetime NOT NULL DEFAULT current_timestamp(),
  `date_updated` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `surname` varchar(150) NOT NULL,
  `email_address` varchar(150) NOT NULL,
  `employee_code` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `is_active` bit(1) NOT NULL DEFAULT b'1',
  `date_created` datetime NOT NULL DEFAULT current_timestamp(),
  `date_updated` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `surname`, `email_address`, `employee_code`, `password`, `is_active`, `date_created`, `date_updated`) VALUES
(1, 'Munyaradzi', 'Chikuruwo', 'roxnmugo@gmail.com', 'N0172515T', '$2a$10$aytcEyOaBmVjhjyOzaJWMeVDGp1t5e42NhXKXTFLjHj4RtoLCVaqS', b'1', '2020-12-02 11:26:19', '2020-12-02 11:26:19'),
(2, 'Nigel', 'Chikuruwo', 'N0172515T@students.nust.ac.zw', 'N0172516T', '$2a$10$S/ZBNsaCGhhNBVg8ZTyaN.Oouenw7AUPZyqaTIqDfcPbjT9pxnko.', b'1', '2020-12-03 07:33:01', '2020-12-03 07:33:01');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES
(1, 1, 1),
(2, 1, 1),
(3, 1, 1),
(4, 2, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activity`
--
ALTER TABLE `activity`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee_id` (`employee_id`),
  ADD KEY `activity_importance` (`activity_importance`),
  ADD KEY `activity_status` (`activity_status`),
  ADD KEY `activity_requests` (`activity_requests`);

--
-- Indexes for table `activity_importance`
--
ALTER TABLE `activity_importance`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `activity_requests`
--
ALTER TABLE `activity_requests`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee_id` (`employee_id`),
  ADD KEY `activity` (`activity`),
  ADD KEY `activity_request_action` (`activity_request_action`),
  ADD KEY `activity_request_status` (`activity_request_status`);

--
-- Indexes for table `activity_request_action`
--
ALTER TABLE `activity_request_action`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `activity_request_status`
--
ALTER TABLE `activity_request_status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `activity_status`
--
ALTER TABLE `activity_status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `annual_activity_calendar`
--
ALTER TABLE `annual_activity_calendar`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `business_unit`
--
ALTER TABLE `business_unit`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `delegation_of_duty`
--
ALTER TABLE `delegation_of_duty`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee_id` (`employee_id`),
  ADD KEY `assign_to` (`assign_to`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `employee_code` (`employee_code`),
  ADD KEY `employment_status_id` (`employment_status_id`),
  ADD KEY `job_title_id` (`job_title_id`);

--
-- Indexes for table `employee_status`
--
ALTER TABLE `employee_status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `job_title`
--
ALTER TABLE `job_title`
  ADD PRIMARY KEY (`id`),
  ADD KEY `business_unit_id` (`business_unit_id`),
  ADD KEY `department_id` (`department_id`);

--
-- Indexes for table `loan`
--
ALTER TABLE `loan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `loan_application_id` (`loan_application_id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `loan_application`
--
ALTER TABLE `loan_application`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee_id` (`employee_id`),
  ADD KEY `loan_type_id` (`loan_type_id`);

--
-- Indexes for table `loan_type`
--
ALTER TABLE `loan_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `monthly_employee_payroll`
--
ALTER TABLE `monthly_employee_payroll`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee_id` (`employee_id`),
  ADD KEY `contract_type` (`contract_type`),
  ADD KEY `job_title_id` (`job_title_id`);

--
-- Indexes for table `next_of_kin`
--
ALTER TABLE `next_of_kin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `notices`
--
ALTER TABLE `notices`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `other_loans/overdrafts`
--
ALTER TABLE `other_loans/overdrafts`
  ADD PRIMARY KEY (`pending_loans_id`);

--
-- Indexes for table `resignation_/_grievances`
--
ALTER TABLE `resignation_/_grievances`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `salary_advance`
--
ALTER TABLE `salary_advance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `salary_advance_application_id` (`salary_advance_application_id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `salary_advance_application`
--
ALTER TABLE `salary_advance_application`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee_id` (`employee_id`),
  ADD KEY `receiving_manager` (`receiving_manager`);

--
-- Indexes for table `spouse_details`
--
ALTER TABLE `spouse_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `staff_transfer`
--
ALTER TABLE `staff_transfer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `releasing_manager` (`releasing_manager`),
  ADD KEY `department_to_assign` (`department_to_assign`),
  ADD KEY `transferring_employee` (`transferring_employee`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email_address` (`email_address`),
  ADD UNIQUE KEY `employee_code` (`employee_code`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `activity`
--
ALTER TABLE `activity`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `activity_importance`
--
ALTER TABLE `activity_importance`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `activity_requests`
--
ALTER TABLE `activity_requests`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `activity_request_action`
--
ALTER TABLE `activity_request_action`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `activity_request_status`
--
ALTER TABLE `activity_request_status`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `activity_status`
--
ALTER TABLE `activity_status`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `annual_activity_calendar`
--
ALTER TABLE `annual_activity_calendar`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `business_unit`
--
ALTER TABLE `business_unit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `delegation_of_duty`
--
ALTER TABLE `delegation_of_duty`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `employee_status`
--
ALTER TABLE `employee_status`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `job_title`
--
ALTER TABLE `job_title`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `loan`
--
ALTER TABLE `loan`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `loan_application`
--
ALTER TABLE `loan_application`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `loan_type`
--
ALTER TABLE `loan_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `monthly_employee_payroll`
--
ALTER TABLE `monthly_employee_payroll`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `next_of_kin`
--
ALTER TABLE `next_of_kin`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `notices`
--
ALTER TABLE `notices`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `other_loans/overdrafts`
--
ALTER TABLE `other_loans/overdrafts`
  MODIFY `pending_loans_id` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `resignation_/_grievances`
--
ALTER TABLE `resignation_/_grievances`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `salary_advance`
--
ALTER TABLE `salary_advance`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `salary_advance_application`
--
ALTER TABLE `salary_advance_application`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `spouse_details`
--
ALTER TABLE `spouse_details`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `staff_transfer`
--
ALTER TABLE `staff_transfer`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user_role`
--
ALTER TABLE `user_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `activity`
--
ALTER TABLE `activity`
  ADD CONSTRAINT `activity_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `activity_ibfk_2` FOREIGN KEY (`activity_importance`) REFERENCES `activity_importance` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `activity_ibfk_3` FOREIGN KEY (`activity_status`) REFERENCES `activity_status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `activity_ibfk_4` FOREIGN KEY (`activity_requests`) REFERENCES `activity_requests` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `activity_requests`
--
ALTER TABLE `activity_requests`
  ADD CONSTRAINT `activity_requests_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `activity_requests_ibfk_2` FOREIGN KEY (`activity`) REFERENCES `activity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `activity_requests_ibfk_3` FOREIGN KEY (`activity_request_action`) REFERENCES `activity_request_action` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `activity_requests_ibfk_4` FOREIGN KEY (`activity_request_status`) REFERENCES `activity_request_status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `delegation_of_duty`
--
ALTER TABLE `delegation_of_duty`
  ADD CONSTRAINT `delegation_of_duty_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `delegation_of_duty_ibfk_2` FOREIGN KEY (`assign_to`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_3` FOREIGN KEY (`employment_status_id`) REFERENCES `employee_status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `employee_ibfk_5` FOREIGN KEY (`job_title_id`) REFERENCES `job_title` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `job_title`
--
ALTER TABLE `job_title`
  ADD CONSTRAINT `job_title_ibfk_1` FOREIGN KEY (`business_unit_id`) REFERENCES `business_unit` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `job_title_ibfk_2` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `loan`
--
ALTER TABLE `loan`
  ADD CONSTRAINT `loan_ibfk_1` FOREIGN KEY (`loan_application_id`) REFERENCES `loan_application` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `loan_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `loan_application`
--
ALTER TABLE `loan_application`
  ADD CONSTRAINT `loan_application_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `loan_application_ibfk_2` FOREIGN KEY (`loan_type_id`) REFERENCES `loan_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `login_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `monthly_employee_payroll`
--
ALTER TABLE `monthly_employee_payroll`
  ADD CONSTRAINT `monthly_employee_payroll_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `monthly_employee_payroll_ibfk_2` FOREIGN KEY (`contract_type`) REFERENCES `employee_status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `monthly_employee_payroll_ibfk_3` FOREIGN KEY (`job_title_id`) REFERENCES `job_title` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `next_of_kin`
--
ALTER TABLE `next_of_kin`
  ADD CONSTRAINT `next_of_kin_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `other_loans/overdrafts`
--
ALTER TABLE `other_loans/overdrafts`
  ADD CONSTRAINT `other_loans/overdrafts_ibfk_1` FOREIGN KEY (`pending_loans_id`) REFERENCES `loan_application` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `resignation_/_grievances`
--
ALTER TABLE `resignation_/_grievances`
  ADD CONSTRAINT `resignation_/_grievances_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `salary_advance`
--
ALTER TABLE `salary_advance`
  ADD CONSTRAINT `salary_advance_ibfk_1` FOREIGN KEY (`salary_advance_application_id`) REFERENCES `salary_advance_application` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `salary_advance_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `salary_advance_application`
--
ALTER TABLE `salary_advance_application`
  ADD CONSTRAINT `salary_advance_application_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `salary_advance_application_ibfk_2` FOREIGN KEY (`receiving_manager`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `spouse_details`
--
ALTER TABLE `spouse_details`
  ADD CONSTRAINT `spouse_details_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `staff_transfer`
--
ALTER TABLE `staff_transfer`
  ADD CONSTRAINT `staff_transfer_ibfk_1` FOREIGN KEY (`releasing_manager`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `staff_transfer_ibfk_2` FOREIGN KEY (`department_to_assign`) REFERENCES `department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `staff_transfer_ibfk_3` FOREIGN KEY (`transferring_employee`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

