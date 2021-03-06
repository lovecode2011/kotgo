package cn.nekocode.kotgo.component.rx

import rx.Observable
import rx.subjects.PublishSubject
import rx.subjects.SerializedSubject
import rx.subjects.Subject

/**
 * @author nekocode (nekocode.cn@gmail.com)
 */
object RxBus {
    private val bus: Subject<Any, Any> = SerializedSubject(PublishSubject.create())

    fun send(o: Any) {
        bus.onNext(o)
    }

    fun toObserverable(): Observable<Any> {
        return bus
    }

    fun <T> toObserverable(classType: Class<T>): Observable<T> {
        return bus.filterByType(classType)
    }

    private fun <T> Observable<Any>.filterByType(classType: Class<T>): Observable<T> {
        return this.filter { classType.isInstance(it) } as Observable<T>
    }
}
