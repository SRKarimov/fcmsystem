package ru.karimov.fuelconsumption

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.util.LinkedMultiValueMap
import ru.karimov.fuelconsumption.domain.entity.Driver
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryConsumption
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryDriver
import ru.karimov.fuelconsumption.usecase.repository.ConsumptionRepository
import ru.karimov.fuelconsumption.usecase.repository.DriverRepository

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApplicationTests(
    @Autowired private val restTemplate: TestRestTemplate,
    @Autowired private val consumptionRepository: InMemoryConsumption,
    @Autowired private val driverRepository: InMemoryDriver
    ) {
        @BeforeEach
        fun beforeTest() {
            consumptionRepository.fetchAll()
            driverRepository.fetchAll()
        }

        @Test
        @DirtiesContext
        fun `should enrol student to course`() {
            // given
//            consumptionRepository.save(
//                dummyStudent().copy(id = 3L)
//            )
            val expectedBody = """{"message":"Student #3 successfully enrolled to class #4"}"""

            // when
            val response = this.restTemplate.postForEntity(
                "/enrol-student-to-course",
                buildEnrolStudentToClassRequest(
                    studentId = 3L,
                    courseId = 4L
                ),
                String::class.java,
                emptyMap<String, String>()
            )

            // then
            assertThat(response.body).isEqualTo(expectedBody)
            assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        }

        @Test
        @DirtiesContext
        fun `should fail to enrol student to unknown course`() {
            // when
            val response = this.restTemplate.postForEntity(
                "/enrol-student-to-course",
                buildEnrolStudentToClassRequest(
                    studentId = 3L,
                    courseId = 4L
                ),
                String::class.java,
                emptyMap<String, String>()
            )
            //then
            assertThat(response.body).isEqualTo("""{"message":"Cannot enrol student #3 to course #4 : course not found","code":"ERR-1"}""")
            assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        }

        @Test
        @DirtiesContext
        fun `should fail to enrol unknown student`() {
            // given
//            consumptionRepository.save(
//                dummyCourse().copy(id = 4L)
//            )
            // when
            val response = this.restTemplate.postForEntity(
                "/enrol-student-to-course",
                buildEnrolStudentToClassRequest(
                    studentId = 3L,
                    courseId = 4L
                ),
                String::class.java,
                emptyMap<String, String>()
            )
            //then
            assertThat(response.body).isEqualTo("""{"message":"Cannot enrol student #3 to course #4 : student not found","code":"ERR-2"}""")
            assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        }

//        @Test
//        @DirtiesContext
//        fun `should fail to enrol student to a course already full`() {
//            // given
//            studentCRUDRepository.save(dummyStudent().copy(id = 1L))
//            studentCRUDRepository.save(dummyStudent().copy(id = 2L))
//            studentCRUDRepository.save(dummyStudent().copy(id = 3L))
//            studentCRUDRepository.save(dummyStudent().copy(id = 4L))
//            studentCRUDRepository.save(dummyStudent().copy(id = 5L))
//            studentCRUDRepository.save(dummyStudent().copy(id = 6L))
//
//            courseCRUDRepository.save(
//                dummyCourse().copy(
//                    id = 4L,
//                    enrolledStudents = mutableListOf(
//                        dummyStudent().copy(id = 1L),
//                        dummyStudent().copy(id = 2L),
//                        dummyStudent().copy(id = 3L),
//                        dummyStudent().copy(id = 4L),
//                        dummyStudent().copy(id = 5L)
//                    )
//                )
//            )
//            val expectedBody = """{"message":"Cannot enrol student #6 to course #4 : course is full","code":"ERR-3"}"""
//
//            // when
//            val response = this.restTemplate.postForEntity(
//                "/enrol-student-to-course",
//                buildEnrolStudentToClassRequest(
//                    studentId = 6L,
//                    courseId = 4L
//                ),
//                String::class.java,
//                emptyMap<String, String>()
//            )
//
//            // then
//            assertThat(response.body).isEqualTo(expectedBody)
//            assertThat(response.statusCode).isEqualTo(HttpStatus.FORBIDDEN)
//        }
//
//        @Test
//        @DirtiesContext
//        fun `should fail to enrol student to a course if already enrolled`() {
//            // given
//            studentCRUDRepository.save(dummyStudent().copy(id = 1L))
//
//            courseCRUDRepository.save(
//                dummyCourse().copy(
//                    id = 4L,
//                    enrolledStudents = mutableListOf(
//                        dummyStudent().copy(id = 1L)
//                    )
//                )
//            )
//            val expectedBody = """{"message":"Cannot enrol student #1 to course #4 : student is already enrolled","code":"ERR-4"}"""
//
//            // when
//            val response = this.restTemplate.postForEntity(
//                "/enrol-student-to-course",
//                buildEnrolStudentToClassRequest(
//                    studentId = 1L,
//                    courseId = 4L
//                ),
//                String::class.java,
//                emptyMap<String, String>()
//            )
//
//            // then
//            assertThat(response.body).isEqualTo(expectedBody)
//            assertThat(response.statusCode).isEqualTo(HttpStatus.FORBIDDEN)
//        }

        private fun dummyCourse(): Driver {
            return Driver(
                id = 1L,
                lastName = "Dummy course for dummies"
            )
        }

        private fun dummyStudent(): Driver {
            return Driver(
                id = 1L,
                firstName = "John",
                lastName = "Doe"
            )
        }

        private fun buildEnrolStudentToClassRequest(studentId: Long, courseId: Long): HttpEntity<*> {
            val requestHeaders = HttpHeaders()
            val body = LinkedMultiValueMap<String, String>()
            body.add("studentId", studentId.toString())
            body.add("courseId", courseId.toString())
            System.out.print(body)
            return HttpEntity<Any>(body, requestHeaders)
        }

    }